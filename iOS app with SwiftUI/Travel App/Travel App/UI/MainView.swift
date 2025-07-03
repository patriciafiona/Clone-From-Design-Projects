//
//  ContentView.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//
// Bottom Nav reference: https://faizannaseem96.medium.com/floating-tabbar-in-swiftui-99ada9595259


import SwiftUI
import Collections

struct MainView: View {
    @EnvironmentObject var navBarState: NavBarState
    @Environment(\.scenePhase) var scenePhase
  
    var tabs: OrderedDictionary = [
        "home": "house",
        "list": "list.clipboard",
        "favorite": "heart",
        "menu": "square.grid.3x3"
    ]
    var tabsTags: [String]
    var tabsImages: [String]
    
    // Location of each curve
    @State var xAxis: CGFloat = 0
    @Namespace var animation
    
    init() {
        UITabBar.appearance().isHidden = true
        
        tabsTags = Array(tabs.keys)
        tabsImages = Array(tabs.values)
    }
    
    //UI section
    var body: some View {
      ZStack(
          alignment: Alignment(
              horizontal: .center,
              vertical: .bottom
          )
      ){
          TabView(selection: $navBarState.selectedTab) {
            HomeTabView()
                .tag(tabsTags[0])
            
            ListTabView()
                .tag(tabsTags[1])
            
            FavoriteTabView()
                .tag(tabsTags[2])
            
            MenuTabView()
                .tag(tabsTags[3])
        }
            
        if(navBarState.isShowNavBar){
          // custom tab bar
          HStack(
            alignment: VerticalAlignment.center,
            spacing: 0
          ) {
            ForEach(Array(tabsImages.enumerated()), id: \.offset) { index, image in
              GeometryReader { reader in
                Button(action: {
                  withAnimation {
                      navBarState.selectedTab = tabsTags[index]
                    xAxis = reader.frame(in: .global).minX
                  }
                }, label: {
                  Image(systemName: image)
                    .resizable()
                    .renderingMode(.template)
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 22.0, height: 22.0)
                    .foregroundColor(
                        navBarState.selectedTab == tabsTags[index] ? .white : .gray)
                    .padding(navBarState.selectedTab == tabsTags[index] ? 15 : 0)
                    .background(Color.black.opacity(navBarState.selectedTab == tabsTags[index] ? 1 : 0).clipShape(Circle()))
                    .matchedGeometryEffect(id: tabsTags[index], in: animation)
                    .offset(x: navBarState.selectedTab == tabsTags[index] ? -10 : 0, y: navBarState.selectedTab == tabsTags[index] ? -50 : 0)
                })
                .onAppear(perform: {
                  if image == tabsImages.first {
                      xAxis = reader.frame(in: .global).minX
                  }
                })
              }
              .frame(width: 25.0, height: 24.0)
              if image != tabsImages.last { Spacer() }
            }
          }
          .padding(.horizontal, 30)
          .padding(.vertical)
          .background(Color.black.clipShape(CustomShape(xAxis: xAxis)).cornerRadius(40.0))
          .padding(.horizontal)
          // Bottom edge....
          .padding(.bottom , UIApplication.shared.windows.first?.safeAreaInsets.bottom)
          }
        }
        .ignoresSafeArea(.all, edges: .all)
    }
}

#Preview {
    MainView()
}
