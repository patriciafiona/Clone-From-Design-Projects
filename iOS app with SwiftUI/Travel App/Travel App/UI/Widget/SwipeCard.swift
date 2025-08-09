//
//  SwipeCard.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import Foundation
import SwiftUI
import SwiftData
import SlideButton

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
  
    @State private var goToDetailPage = false
    @State private var isImageLoading = false
    
    @Environment(\.modelContext) private var modelContext
    @Query private var favPlace: [FavoritePlaceModel]

    var body: some View {
        let isInList = favPlace.filter { $0.placeId == model.place.id }
        
        //Slide button style
        let styling = SlideButton<Self>.Styling(
          indicatorSize: 54,
          indicatorSpacing: 5,
          indicatorColor: .black,
          backgroundColor: .black.opacity(0.3),
          textColor: .white,
          indicatorSystemName: "chevron.right",
          indicatorDisabledSystemName: "xmark",
          textAlignment: .center,
          textFadesOpacity: true,
          textHiddenBehindIndicator: true,
          textShimmers: false
        )
      
        ZStack{
            CachedAsyncImage(
                url: URL(string: model.place.image),
                isImageLoading: $isImageLoading
            )
            .frame(width: size.width * 0.8, height: size.height)
          
            if !isImageLoading {
                VStack(alignment: .leading){
                    //MARK: Favorite Button
                    HStack{
                        Spacer()
                        
                        Button(action: {
                            //Check if already fav or not
                            do{
                                let currentPlace = FavoritePlaceModel(
                                    placeId: model.place.id
                                )
                                
                                if(isInList.count == 0){
                                    print("Add to fav place...")
                                    
                                    //Add or remove from the list of favorite
                                    modelContext.insert(currentPlace)
                                }else{
                                    print("Delete from fav place...")
                                    
                                    //remove from the fav list
                                    modelContext.delete(isInList.first!)
                                    try modelContext.save()
                                }
                            }catch{
                                print("Error on handling update favorite: \(error)")
                            }
                        }, label: {
                            if (isInList.count == 0){
                                Image(systemName: "heart")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 25, height: 25)
                                    .padding(7.5)
                            }else{
                                Image(systemName: "heart.fill")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 25, height: 25)
                                    .padding(7.5)
                            }
                        })
                        .buttonStyle(.borderedProminent)
                        .clipShape(Circle())
                        .tint(isInList.count == 0 ? .white.opacity(0.2) : .red.opacity(0.2))
                        .foregroundStyle(isInList.count == 0 ? .white : .red)
                    }
                    
                    Spacer()
                    
                    //MARK: Country Initial
                    Text(model.place.country)
                        .font(.Inter_Paragraph)
                        .foregroundColor(.white)
                    
                    //MARK: Place Name
                    Text(model.place.name)
                        .font(.CalSans_Title02)
                        .foregroundColor(.white)
                    
                    //MARK: Rating & review
                    HStack{
                        HStack{
                            Image(systemName: "star")
                                .resizable()
                                .scaledToFit()
                                .frame(width: 16, height: 16)
                            
                            Text(String(format: "%.2f", model.place.rating))
                                .font(.Inter_Paragraph)
                                .foregroundColor(.white)
                        }
                        .padding(EdgeInsets(top: 4, leading: 8, bottom: 4, trailing: 8))
                        .overlay(
                            RoundedRectangle(cornerRadius: 50)
                                .stroke(.white, lineWidth: 2)
                        )
                        .foregroundStyle(.white)
                        .buttonBorderShape(.roundedRectangle(radius: 50))
                        
                        Text("143 reviews")
                            .font(.Inter_Paragraph)
                            .foregroundColor(.white)
                        
                        Spacer()
                    }
                    .padding(EdgeInsets(top: 0, leading: 0, bottom: 8, trailing: 0))
                    
                    //MARK: Slide button
                    SlideButton("See more", styling: styling, action: {
                        goToDetailPage = true
                    })
                    
                    NavigationLink("Go to next page", destination: DetailScreenView(data: model.place), isActive: $goToDetailPage)
                        .hidden()
                }
                .padding(16)
            }
          
        }
        .frame(width: size.width * 0.8, height: size.height)
        .background(Color.white)
        .cornerRadius(15)
        .foregroundColor(.black)
        .padding()
    }
    
}
