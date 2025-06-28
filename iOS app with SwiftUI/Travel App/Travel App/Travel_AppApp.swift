//
//  Travel_AppApp.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import SwiftUI

@main
struct Travel_AppApp: App {
    var navBarState = NavBarState()
  
    var body: some Scene {
        WindowGroup {
            MainView()
              .environmentObject(navBarState) // Inject here
        }
    }
}
