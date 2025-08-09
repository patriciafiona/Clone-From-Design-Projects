//
//  IconTextTag.swift
//  Travel App
//
//  Created by FIONA Patricia on 24/04/25.
//

import SwiftUI

struct IconTextTag: View {
    @Binding var selectedContinent: String
    
    let icon: String
    let text: String
    
    var body: some View {
        HStack(spacing: 8){
            Image(systemName: icon)
                .resizable()
                .frame(width: 20, height: 20)
                .foregroundColor(
                    selectedContinent == text ? .white : .gray)
            
            Text(text)
                .font(.Inter_Title03)
                .foregroundColor(
                    selectedContinent == text ? .white : .gray)
            
        }
        .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        .background(selectedContinent == text ? .black : .white)
        .cornerRadius(25)
        .shadow(color:Color.gray.opacity(0.2), radius: 10, x: 0, y: 3)
        .padding(8)
        .onTapGesture {
            selectedContinent = text
        }
    }
}

#Preview {
    @State var selectedContinent = "Asia"
    
    IconTextTag(
        selectedContinent: $selectedContinent,
        icon: "globe.americas.fill",
        text: "America"
    )
}
