//
//  FavoriteTabView.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import SwiftData
import SwiftUI

struct FavoriteTabView: View {
    @Environment(\.modelContext) private var modelContext
    @Query private var favPlace: [FavoritePlaceModel]
    
    var body: some View {
        NavigationView{
            VStack (alignment: .center){
                Text("My Favorite")
                    .font(.CalSans_Title01.bold())
                
                if(favPlace.isEmpty){
                    Spacer()
                    
                    Image(systemName: "list.bullet.clipboard")
                        .font(.system(size: 50))
                        .foregroundStyle(.black)
                        .padding()
                    
                    Text("No Favorite Place")
                        .font(.Inter_Medium02.bold())
                    
                    Spacer()
                }else{
                    ScrollView {
                        LazyVStack(spacing: 8){
                            ForEach(favPlace, id: \.self) { place in
                                FavoritePlaceItem(placeId: place.placeId)
                            }
                        }
                    }
                }
            }
            .padding(16)
        }
    }
}

#Preview {
    FavoriteTabView()
}
