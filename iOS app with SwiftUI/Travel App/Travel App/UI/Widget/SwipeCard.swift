//
//  SwipeCard.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import Foundation
import SwiftUI

struct CardView: View {
    enum SwipeDirection {
        case left, right, none
    }

    struct Model: Identifiable, Equatable {
        let id = UUID()
        let text: String
        var swipeDirection: SwipeDirection = .none
    }

    var model: Model
    var size: CGSize
    var dragOffset: CGSize
    var isTopCard: Bool
    var isSecondCard: Bool

    var body: some View {
        Text(model.text)
            .frame(width: size.width * 0.8, height: size.height)
            .background(Color.white)
            .cornerRadius(15)
            .shadow(color: isTopCard ? getShadowColor() : (isSecondCard && dragOffset.width != 0 ? Color.gray.opacity(0.2) : Color.clear), radius: 10, x: 0, y: 3)
            .foregroundColor(.black)
            .font(.largeTitle)
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
