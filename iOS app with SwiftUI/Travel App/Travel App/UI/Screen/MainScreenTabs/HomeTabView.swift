//
//  HomeTabView.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import SwiftUI
import Collections

struct HomeTabView: View {
    let userName = "Abignail"
    let continents: OrderedDictionary = [
        "Asia": "globe.asia.australia.fill",
        "America": "globe.americas.fill",
        "Africa": "globe.europe.africa.fill",
        "Australia": "globe.asia.australia.fill",
        "Europe": "globe.europe.africa.fill"
    ]
    
    @State var selectedContinent = "Asia"
    @State private var searchText: String = ""
    let placeholder = "Search"
    
    init(){
        UITextView.appearance().backgroundColor = .clear
    }
    
    var body: some View {
        VStack(
            alignment: HorizontalAlignment.leading
        ){
            HStack(
                alignment:VerticalAlignment.center
            ){
                VStack(
                    alignment: HorizontalAlignment.leading
                ){
                    Text("Hello, \(userName)")
                        .font(.CalSans_Title01.bold())
                    Text("Welcome to the TripGuide")
                        .font(.Inter_Subtitle)
                }
                
                Spacer()
                
                Image("profilePicture")
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width: 45, height: 45)
                    .clipShape(Circle())
            }
            
            HStack(spacing: 4){
                Image(systemName: "magnifyingglass")
                    .padding(8)
                
                TextField(
                  placeholder,
                  text: $searchText,
                  onCommit: {
                      //code here
                  }
                ).padding()
                
                Button(action: {
                    // Action to perform when the button is tapped
                  }) {
                    Image(systemName: "line.3.horizontal.decrease.circle")
                      .padding(8)
                      .foregroundColor(.white)
                      .background(.black)
                      .clipShape(Circle())
                  }
            }
            .padding(8)
            .frame(
                height: 50
            )
            .background(Color.white)
            .cornerRadius(25)
            .shadow(color:Color.gray.opacity(0.2), radius: 10, x: 0, y: 3)
            .padding(EdgeInsets(top: 12, leading: 0, bottom: 12, trailing: 0))
            
            Text("Select your next trip")
                .font(.CalSans_Title02)
            
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 2) {
                    ForEach(Array(continents.keys.enumerated()), id: \.offset) { index, name in
                        IconTextTag(
                            selectedContinent: $selectedContinent,
                            icon: Array(continents.values)[index],
                            text: name
                        )
                    }
                }
            }
            
            GeometryReader { geometry in
                VStack {
                    let cards = [
                        CardView.Model(text: "Card 1"),
                        CardView.Model(text: "Card 2"),
                        CardView.Model(text: "Card 3"),
                        CardView.Model(text: "Card 4")
                    ]
                    
                    let model = SwipeableCardsView.Model(cards: cards)
                    SwipeableCardsView(model: model) { model in
                        print(model.swipedCards)
                        model.reset()
                    }
                }
                .frame(
                    width: geometry.size.width,
                    height: geometry.size.height * 0.75
                )
            }
        }.padding()
    }
}

#Preview {
    HomeTabView()
}
