//
//  ListTabView.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import SwiftUI

struct ListTabView: View {
    let places = TravelDatabase.init().allTravels
    
    // Define the grid layout
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    
    var body: some View {
        NavigationView{
            ScrollView {
                Text("All Places")
                    .font(.CalSans_Title01.bold())
                    .padding(.bottom)
                
                LazyVGrid(columns: columns, spacing: 20) {
                    ForEach(places, id: \.id) { item in
                        PlaceGridView(place: item)
                    }
                }
                .padding()
                .padding(.bottom, 80)
            }
        }
    }
}

#Preview {
    ListTabView()
}
