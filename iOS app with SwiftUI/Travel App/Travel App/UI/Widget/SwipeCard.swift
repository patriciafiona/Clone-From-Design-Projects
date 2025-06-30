//
//  SwipeCard.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import Foundation
import SwiftUI
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


    var body: some View {
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
                        
                        Button(action: {}, label: {
                            Image(systemName: "heart")
                                .resizable()
                                .scaledToFit()
                                .frame(width: 25, height: 25)
                                .padding(7.5)
                        })
                        .buttonStyle(.borderedProminent)
                        .clipShape(Circle())
                        .tint(.white.opacity(0.2))
                        .foregroundStyle(.white)
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
        .shadow(color: isTopCard ? getShadowColor() : (isSecondCard && dragOffset.width != 0 ? Color.gray.opacity(0.2) : Color.clear), radius: 10, x: 0, y: 3)
        .foregroundColor(.black)
        .padding()
    }
    
    private func getShadowColor() -> Color {
        if dragOffset.width > 0 {
            return Color.green.opacity(0.5)
        } else if dragOffset.width < 0 {
            return Color.red.opacity(0.5)
        } else {
            return Color.gray.opacity(0.2)
        }
    }
}
