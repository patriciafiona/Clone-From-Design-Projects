//
//  HomeTabView.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import SwiftUI

struct HomeTabView: View {
    var body: some View {
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
        .padding()
    }
}

#Preview {
    HomeTabView()
}
