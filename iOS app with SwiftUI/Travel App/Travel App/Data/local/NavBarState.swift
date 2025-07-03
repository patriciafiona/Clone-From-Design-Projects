//
//  NavBarState.swift
//  Travel App
//
//  Created by Patricia Fiona on 28/06/25.
//

import Foundation

class NavBarState: ObservableObject {
  @Published var isShowNavBar: Bool = true
  @Published var selectedTab: String = "home"
}
