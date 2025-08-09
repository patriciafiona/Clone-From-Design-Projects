//
//  PlaceGridView.swift
//  Travel App
//
//  Created by Patricia Fiona on 07/08/25.
//

import SwiftUI

struct PlaceGridView: View {
    let place: Place
    let cardWidth: CGFloat = 180
    let cardHeight: CGFloat = 250
    
    @State private var isImageLoading = false
    
    var body: some View {
        NavigationLink(destination: DetailScreenView(data: place)){
            ZStack{
                ZStack(alignment: .bottom){
                    CachedAsyncImage(
                        url: URL(string: place.image),
                        isImageLoading: $isImageLoading
                    )
                    .frame(width: cardWidth, height: cardHeight)
                    
                    // Gradient hitam di bagian bawah
                    LinearGradient(
                        gradient: Gradient(colors: [Color.clear, Color.black.opacity(0.5)]),
                        startPoint: .top,
                        endPoint: .bottom
                    )
                    .frame(height: 150)
                }
                
                if !isImageLoading {
                    VStack(alignment: .leading){
                        Spacer()
                        
                        //MARK: Country Initial
                        Text(place.country)
                            .font(.Inter_Paragraph)
                            .foregroundColor(.white)
                        
                        //MARK: Place Name
                        Text(place.name)
                            .font(.CalSans_Subtitle)
                            .foregroundColor(.white)
                        
                        //MARK: Rating & review
                        HStack{
                            HStack{
                                Image(systemName: "star")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 12, height: 12)
                                
                                Text(String(format: "%.2f", place.rating))
                                    .font(.CalSans_Paragraph)
                                    .foregroundColor(.white)
                            }
                            .padding(EdgeInsets(top: 3, leading: 6, bottom: 3, trailing: 6))
                            .overlay(
                                RoundedRectangle(cornerRadius: 50)
                                    .stroke(.white, lineWidth: 2)
                            )
                            .foregroundStyle(.white)
                            .buttonBorderShape(.roundedRectangle(radius: 50))
                            
                            Text("143 reviews")
                                .font(.CalSans_Paragraph)
                                .foregroundColor(.white)
                            
                            Spacer()
                        }
                        .padding(EdgeInsets(top: 0, leading: 0, bottom: 8, trailing: 0))
                        
                    }
                    .padding(16)
                }
                
            }
            .frame(maxWidth: cardWidth, maxHeight: cardHeight)
            .background(Color.white)
            .cornerRadius(15)
            .foregroundColor(.black)
            .padding()
        }
    }
}

#Preview {
    let example = TravelDatabase().allTravels.first!
    
    PlaceGridView(place: example)
}
