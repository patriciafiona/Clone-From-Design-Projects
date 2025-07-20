//
//  NavBarState.swift
//  Travel App
//
//  Created by Patricia Fiona on 28/06/25.
//

import Foundation

class NavBarState: ObservableObject {
    @Published var isFirstOpen: Bool = true
    @Published var isShowNavBar: Bool = true
    @Published var selectedTab: String = "home"
    @Published var xAxis: CGFloat = 0
}
