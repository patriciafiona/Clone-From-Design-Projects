//
//  MenuTabView.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import SwiftUI

struct SettingsTabView: View {
    // MARK: - PROPERTIES
    
    private let alternateAppIcons: [String] = [
      "AppIcon-MagnifyingGlass",
      "AppIcon-Map",
      "AppIcon-Mushroom",
      "AppIcon-Camera",
      "AppIcon-Backpack",
      "AppIcon-Campfire"
    ]
    
    let year = Calendar.current.component(.year, from: Date())
    
    var body: some View {
      List {
        // MARK: - SECTION: HEADER
        
        Section {
          HStack {
            Spacer()
            
            Image(systemName: "mountain.2.fill")
              .font(.system(size: 20, weight: .black))
            
            VStack(spacing: -7) {
              Text("Travel App")
                .font(.system(size: 44, weight: .black))
              
              Text("Editors' Choice")
                .fontWeight(.medium)
            }
            
            Image(systemName: "mountain.2.fill")
                .font(.system(size: 20, weight: .black))
            
            Spacer()
          }
          .foregroundStyle(
            LinearGradient(
              colors: [
                .customGrayLight,
                .customGrayMedium,
                Color.black
              ],
              startPoint: .top,
              endPoint: .bottom
            )
          )
          .padding(.top, 8)
          
          VStack(spacing: 8) {
            Text("Where can you find \nperfect trip?")
              .font(.title2)
              .fontWeight(.heavy)
            
            Text("The destination which looks gorgeous in photos but is even better once you are actually there. The 'Travel' that you hope to do again someday. \nFind the best day places in the app.")
              .font(.footnote)
              .italic()
            
            Text("Find Your Next Breathtaking Moment")
              .fontWeight(.heavy)
              .foregroundColor(.customGrayMedium)
          }
          .multilineTextAlignment(.center)
          .padding(.bottom, 16)
          .frame(maxWidth: .infinity)
        } //: HEADER
        .listRowSeparator(.hidden)
        
        // MARK: - SECTION: ABOUT
        
        Section(
          header: Text("ABOUT THE APP"),
          footer: HStack {
            Spacer()
            Text(
                String(format: "© %d Patricia Fiona. All rights reserved.", year)
            )
            Spacer()
          }
            .padding(.vertical, 8)
        ) {
          // 1. Basic Labeled Content
          // LabeledContent("Application", value: "Hike")
          
          // 2. Advanced Labeled Content
          
          CustomListRowView(rowLabel: "Application", rowIcon: "apps.iphone", rowContent: "Travel App", rowTintColor: .blue)
          
          CustomListRowView(rowLabel: "Compatibility", rowIcon: "info.circle", rowContent: "iOS, iPadOS", rowTintColor: .red)
          
          CustomListRowView(rowLabel: "Technology", rowIcon: "swift", rowContent: "Swift", rowTintColor: .orange)
          
          CustomListRowView(rowLabel: "Version", rowIcon: "gear", rowContent: "1.0", rowTintColor: .purple)
          
          CustomListRowView(rowLabel: "Developer", rowIcon: "ellipsis.curlybraces", rowContent: "Patricia Fiona", rowTintColor: .mint)
          
          CustomListRowView(rowLabel: "Designer", rowIcon: "paintpalette", rowContent: "Ronas IT\nRobert Petras\nPatricia Fiona", rowTintColor: .pink)
          
          CustomListRowView(rowLabel: "Insipiration", rowIcon: "globe", rowTintColor: .indigo, rowLinkLabel: "Dribble", rowLinkDestination: "https://dribbble.com/shots/24911825-Travel-Mobile-App")
          
        } //: SECTION
      } //: LIST
      .padding(EdgeInsets(top: 0, leading: 0, bottom: 20, trailing: 0))
    }
}

#Preview {
    SettingsTabView()
}
