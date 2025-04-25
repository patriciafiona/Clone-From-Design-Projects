//
//  SwipeCard.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import Foundation
import SwiftUI

struct CardView: View {
    enum SwipeDirection {
        case left, right, none
    }

    struct Model: Identifiable, Equatable {
        let id = UUID()
        let place: Place
        var swipeDirection: SwipeDirection = .none
    }

    var model: Model
    var size: CGSize
    var dragOffset: CGSize
    var isTopCard: Bool
    var isSecondCard: Bool

    var body: some View {
        ZStack{
            AsyncImage(url: URL(string: model.place.image)) { phase in
                if let image = phase.image {
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: size.width * 0.8, height: size.height)
                } else if phase.error != nil {
                    Text("No image available")
                } else {
                    ProgressView()
                      .progressViewStyle(CircularProgressViewStyle(tint: .blue))
                      .scaleEffect(2.0, anchor: .center) // Makes the spinner larger
                }
            }
            .frame(width: size.width * 0.8, height: size.height)
        }
        .frame(width: size.width * 0.8, height: size.height)
        .background(Color.white)
        .cornerRadius(15)
        .shadow(color: isTopCard ? getShadowColor() : (isSecondCard && dragOffset.width != 0 ? Color.gray.opacity(0.2) : Color.clear), radius: 10, x: 0, y: 3)
        .foregroundColor(.black)
        .padding()
    }
    
    private func getShadowColor() -> Color {
        if dragOffset.width > 0 {
            return Color.green.opacity(0.5)
        } else if dragOffset.width < 0 {
            return Color.red.opacity(0.5)
        } else {
            return Color.gray.opacity(0.2)
        }
    }
}
