//
//  FavoritePlaceItem.swift
//  Travel App
//
//  Created by FIONA Patricia on 02/07/25.
//

import SwiftUI
import SwiftData

struct FavoritePlaceItem: View {
    
    @State private var isImageLoading = false
    @State private var goToDetailPage = false
    
    @Environment(\.modelContext) private var modelContext
    @Query private var favPlace: [FavoritePlaceModel]
    
    let placeId: String
    var place: Place? = nil
    
    init(placeId: String) {
        self.placeId = placeId
        
        do{
            self.place = try TravelDatabase().getPlacesById(id: placeId)
        }catch {
            print("Error get favorite place by Id")
        }
    }
    
    var body: some View {
        let screenSize = UIScreen.main.bounds
        let screenWidth = screenSize.width
        let screenHeight = screenSize.height
        
        let isInList = favPlace.filter { $0.placeId == place!.id }
        
        if(place != nil){
            HStack {
                ZStack{
                    CachedAsyncImage(url: URL(string: place!.image), isImageLoading: $isImageLoading)
                        .frame(width: 130, height: 130)
                }
                .padding(EdgeInsets(top: 24, leading: 16, bottom: 0, trailing: 16))
                .frame(width: 120, height: 80)
                .clipShape(RoundedRectangle(cornerRadius: 16))
                
                VStack (alignment: .leading){
                    Text(place!.name)
                        .font(.Inter_Medium01)
                        .bold()
                    
                    Text(place!.decription)
                        .font(.Inter_Paragraph)
                        .lineLimit(2)
                        .truncationMode(.tail)
                        .foregroundColor(.gray)
                    
                    Button(
                        action: {
                            print("Go to favorite place...")
                            goToDetailPage = true
                        }
                    ){
                        Text("See detail")
                            .font(.Inter_Paragraph)
                    }
                    .padding(EdgeInsets(top: 8, leading: 0, bottom: 0, trailing: 0))
                }
                
                NavigationLink("Go to next page", destination: DetailScreenView(data: place!), isActive: $goToDetailPage)
                    .hidden()
            }
            .padding(EdgeInsets(top: 16, leading: 32, bottom: 16, trailing: 32))
            .frame(width: screenWidth, height: 130)
            .background(.thinMaterial)
            .padding(EdgeInsets(top: 4, leading: 0, bottom: 4, trailing: 0))
            .contentShape(Rectangle())
            .addSwipeAction(edge: .trailing) {
                Button {
                    do{
                        //remove from the fav list
                        modelContext.delete(isInList.first!)
                        try modelContext.save()
                    }catch{
                        print("Error on handling update favorite: \(error)")
                    }
                } label: {
                    Image(systemName: "trash")
                        .foregroundColor(.white)
                }
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 8))
                .frame(width: 100, height: 130, alignment: .center)
                .contentShape(Rectangle())
                .background(Color.red)
            }
            
        }
    }
}

#Preview {
    FavoritePlaceItem(placeId: "PLC_FRA_0001")
}
