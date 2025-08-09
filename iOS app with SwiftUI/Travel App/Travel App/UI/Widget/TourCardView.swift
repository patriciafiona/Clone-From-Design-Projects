//
//  TourCardView.swift
//  Travel App
//
//  Created by Patricia Fiona on 01/07/25.
//

import SwiftUI

struct TourCardView: View {
    
    @State private var isImageLoading = false
    
    var tour: Tour
    
    var body: some View {
        VStack {
            ZStack{
                CachedAsyncImage(url: URL(string: tour.image), isImageLoading: $isImageLoading)
                    .frame(width: 250, height: 180)
            }
            .padding(EdgeInsets(top: 24, leading: 16, bottom: 0, trailing: 16))
            .frame(width: 240, height: 160)
            .clipShape(RoundedRectangle(cornerRadius: 16))
            
            HStack{
                VStack (alignment: .leading){
                    Text(tour.title)
                        .font(.Inter_Medium01)
                        .bold()
                    
                    HStack (alignment: .center){
                        Text("\(tour.totalDays) days")
                            .font(.Inter_Paragraph)
                            .foregroundColor(.gray)
                        
                        Image(systemName: "circle.fill")
                            .font(.system(size: 6))
                            .foregroundColor(.gray)
                        
                        Text("from $\(String(format: "%.2f", tour.price))/person")
                            .font(.Inter_Paragraph)
                            .foregroundColor(.gray)
                    }
                    
                    HStack{
                        HStack{
                            Image(systemName: "star")
                                .resizable()
                                .scaledToFit()
                                .frame(width: 16, height: 16)
                            
                            Text(String(format: "%.2f", tour.rating))
                                .font(.Inter_Paragraph)
                                .foregroundColor(.black)
                        }
                        .padding(EdgeInsets(top: 4, leading: 8, bottom: 4, trailing: 8))
                        .overlay(
                            RoundedRectangle(cornerRadius: 50)
                                .stroke(.black)
                        )
                        .foregroundStyle(.black)
                        .buttonBorderShape(.roundedRectangle(radius: 50))
                        
                        Text("143 reviews")
                            .font(.Inter_Paragraph)
                            .bold()
                            .underline()
                    }
                }
                
                Spacer()
                
                Button(action: {
                  // Action to perform when the button is tapped
                }) {
                  Image(systemName: "arrow.right")
                    .frame(width: 28, height: 28)
                    .padding(8)
                    .foregroundColor(.white)
                    .background(.black)
                    .clipShape(Circle())
                }
            }
            .padding(EdgeInsets(top: 8, leading: 16, bottom: 16, trailing: 16))
            .frame(width: 270)
        }
        .padding(EdgeInsets(top: 4, leading: 8, bottom: 4, trailing: 8))
        .frame(width: 270, height: 270)
        .background(Color.white)
        .cornerRadius(16)
        .shadow(radius: 4)
    }
}

#Preview {
    let tour = Tour(
        title: "Dream Paraty",
        image: "https://www.thesmoothescape.com/wp-content/uploads/2025/05/Saco-do-Mamangua-Paraty.jpg",
        totalDays: 5,
        price: 427.18,
        rating: 4.7
    )
    
    TourCardView(tour: tour)
}
