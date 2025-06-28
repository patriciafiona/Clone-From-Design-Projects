//
//  DetailScreenView.swift
//  Travel App
//
//  Created by Patricia Fiona on 28/06/25.
//

import SwiftUI

struct DetailScreenView: View {
    @EnvironmentObject var navBarState: NavBarState
    @Environment(\.dismiss) var dismiss
  
    @State private var sheetShown = true
  
    var data: Place
  
    var body: some View {
      let screenSize = UIScreen.main.bounds
      let screenWidth = screenSize.width
      let screenHeight = screenSize.height
      
      NavigationView{
        ZStack(alignment: .top){
          VStack{
            ZStack(alignment: .bottom) {
              CachedAsyncImage(url: URL(string: data.image))
                .frame(width: screenWidth, height: 450)

                // Gradient hitam di bagian bawah
                LinearGradient(
                    gradient: Gradient(colors: [Color.clear, Color.black]),
                    startPoint: .top,
                    endPoint: .bottom
                )
                .frame(height: 100)
            }
            .frame(width: screenWidth, height: 450)
            
            Spacer()
          }
          .background(.black)
          .offset(x: 0, y: 0)
          
          HStack{
            Button(
              action: {
                dismiss()
              },
              label: {
               Image(systemName: "chevron.left")
                   .resizable()
                   .scaledToFit()
                   .frame(width: 20, height: 20)
                   .padding(7.5)
              })
            .buttonStyle(.borderedProminent)
            .clipShape(Circle())
            .tint(.white)
            .foregroundStyle(.black)
            
            Spacer()
            
            Button(action: {}, label: {
               Image(systemName: "heart")
                   .resizable()
                   .scaledToFit()
                   .frame(width: 20, height: 20)
                   .padding(7.5)
           })
            .buttonStyle(.borderedProminent)
            .clipShape(Circle())
            .tint(.white)
            .foregroundStyle(.black)
          }
          .frame(width: screenWidth, alignment: .top)
          .offset(x: 0, y: 0)
          .padding(EdgeInsets(top: 60, leading: 36, bottom: 0, trailing: 36))
        }
        .edgesIgnoringSafeArea(.top)
        .sheet(isPresented: $sheetShown) {
            NavigationStack {
              Text(data.name)
                .font(.CalSans_Title02)
                .foregroundColor(.black)
            }
            .presentationDetents([.medium, .custom(MyCustomDetent.self)])
            .presentationCornerRadius(36)
        }
      }
      .onAppear(){
        navBarState.isShowNavBar = false
      }
      .navigationBarBackButtonHidden(true)
    }
}

struct MyCustomDetent: CustomPresentationDetent {
    static func height(in context: Context) -> CGFloat? {
        if context.dynamicTypeSize.isAccessibilitySize {
            return context.maxDetentValue
        } else {
            return context.maxDetentValue * 0.8
        }
    }
}

#Preview {
  let example = TravelDatabase().allTravels.first!
  
  DetailScreenView(data: example)
}
