//
//  CachedAsyncImage.swift
//  Travel App
//
//  Created by FIONA Patricia on 28/04/25.
//
// Link: https://medium.com/@jakir/enable-image-cache-in-asyncimage-swiftui-db4b9c34603f

import Foundation
import SwiftUI

public struct CachedAsyncImage: View {
    private let url: URL?
    @State private var image: Image? = nil
    @Binding var isLoading: Bool

    public init(url: URL?, isImageLoading: Binding<Bool>) {
        self.url = url
        self._isLoading = isImageLoading
        
        //Automatic set to true for loading
        isLoading = true
    }

    public var body: some View {
        if let image = image {
            image
                .resizable()
                .aspectRatio(contentMode: .fill)
        } else {
            ProgressView()
                .onAppear {
                    Task {
                        await loadImage()
                    }
                }
              .progressViewStyle(CircularProgressViewStyle(tint: .blue))
              .scaleEffect(2.0, anchor: .center) // Makes the spinner larger
        }
    }

    private func loadImage() async {
        guard let url = url, !isLoading else { return }

        // Ensure state updates are isolated to the main actor
        isLoading = true

        // Check if the image is already cached
        let request = URLRequest(url: url)
        if let cachedResponse = URLCache.shared.cachedResponse(for: request),
           let cachedImage = UIImage(data: cachedResponse.data) {
            await MainActor.run {
                self.image = Image(uiImage: cachedImage)
                self.isLoading = false
            }
            return
        }

        // Fetch the image from the network
        do {
            let (data, response) = try await URLSession.shared.data(for: request)

            // Cache the image
            let cachedData = CachedURLResponse(response: response, data: data)
            URLCache.shared.storeCachedResponse(cachedData, for: request)

            if let uiImage = UIImage(data: data) {
                await MainActor.run {
                    self.image = Image(uiImage: uiImage)
                    self.isLoading = false
                }
            }
        } catch {
            // Handle any errors here (e.g., network failure)
            await MainActor.run {
                self.isLoading = false
            }
        }
    }
}
