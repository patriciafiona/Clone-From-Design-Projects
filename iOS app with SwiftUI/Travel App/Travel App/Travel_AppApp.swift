//
//  Travel_AppApp.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import SwiftUI
import SwiftData
import Toasts

@main
struct Travel_AppApp: App {
    var navBarState = NavBarState()
  
    var body: some Scene {
        WindowGroup {
            MainView()
              .environmentObject(navBarState) // Inject here
              .installToast(position: .bottom)
        }
        // The modelContainer holds the array of each Type of data that we want to read from the container
        .modelContainer(for: [FavoritePlaceModel.self])
    }
}
