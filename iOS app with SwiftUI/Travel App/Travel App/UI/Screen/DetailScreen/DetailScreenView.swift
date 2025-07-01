//
//  DetailScreenView.swift
//  Travel App
//
//  Created by Patricia Fiona on 28/06/25.
//

import SwiftUI
import FlagsKit
import ExpandableText

struct DetailScreenView: View {
    @EnvironmentObject var navBarState: NavBarState
    @Environment(\.dismiss) var dismiss
  
    @State private var sheetShown = true
    @State private var isImageLoading = false
  
    var data: Place
  
    var body: some View {
      let screenSize = UIScreen.main.bounds
      let screenWidth = screenSize.width
      let screenHeight = screenSize.height
      
      NavigationView{
        ZStack(alignment: .top){
          VStack{
            ZStack(alignment: .bottom) {
                CachedAsyncImage(url: URL(string: data.image), isImageLoading: $isImageLoading)
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
                  navBarState.isShowNavBar = true
                  dismiss()
              },
              label: {
               Image(systemName: "chevron.left")
                   .resizable()
                   .scaledToFit()
                   .frame(width: 20, height: 20)
                   .padding(5)
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
          .padding(EdgeInsets(top: 60, leading: 40, bottom: 0, trailing: 40))
        }
        .edgesIgnoringSafeArea(.top)
        .sheet(isPresented: $sheetShown) {
            DetailSheet(data: data)
        }
      }
      .onAppear(){
        navBarState.isShowNavBar = false
      }
      .onDisappear(){
          navBarState.isShowNavBar = true
      }
      .navigationBarBackButtonHidden(true)
    }
}

struct DetailSheet: View {
    var data: Place
    
    var body: some View {
        NavigationStack {
            ScrollView{
                VStack (alignment: .leading){
                    //MARK: PLACE NAME & RATING
                    HStack {
                        Text(data.name)
                          .font(.CalSans_Title02)
                          .foregroundColor(.black)
                        
                        Spacer()
                        
                        HStack{
                            Image(systemName: "star")
                                .resizable()
                                .scaledToFit()
                                .frame(width: 16, height: 16)
                            
                            Text(String(format: "%.2f", data.rating))
                                .font(.Inter_Paragraph)
                                .foregroundColor(.black)
                        }
                        .padding(EdgeInsets(top: 4, leading: 8, bottom: 4, trailing: 8))
                        .overlay(
                            RoundedRectangle(cornerRadius: 50)
                                .stroke(.white.opacity(0.3), lineWidth: 2)
                        )
                        .foregroundStyle(.black)
                        .buttonBorderShape(.roundedRectangle(radius: 50))
                    }
                    
                    //MARK: COUNTRY & REVIEW
                    HStack{
                        //Get country alpha-2
                        let locale = Locale.current
                        let alpha3 = data.country
                        
                        let countryName = locale.localizedString(forRegionCode: alpha3) ?? "Unknown"
                        let alpha2 = Locale.autoupdatingCurrent.alpha2Code(from: alpha3) ?? ""
                        
                        FlagView(countryCode: alpha2, style: .circle)
                            .frame(width: 20, height: 20)
                            .previewLayout(.sizeThatFits)
                        
                        Text(countryName)
                            .font(.Inter_Medium01)
                            .bold()
                        
                        Spacer()
                        
                        Text("143 reviews")
                            .font(.Inter_Medium01)
                            .bold()
                            .underline()
                    }
                    
                    ExpandableText(data.decription)
                      .font(.Inter_Paragraph)
                      .foregroundColor(.black)
                      .lineLimit(3)
                      .moreButtonText("Read more")
                      .moreButtonFont(.headline.bold())
                      .moreButtonColor(.black)
                      .enableCollapse(true)
                      .expandAnimation(.easeInOut(duration: 0.2))
                      .trimMultipleNewlinesWhenTruncated(true)
                      .padding(EdgeInsets(top: 8, leading: 0, bottom: 16, trailing: 0))
                    
                    
                    //MARK: UPCOMINH TOURS
                    HStack{
                        Text("Upcoming tours")
                            .font(.CalSans_Title02)
                            .foregroundColor(.black)
                        
                        Spacer()
                        
                        Button(
                            action: {}
                        ){
                            Text("See all")
                                .underline()
                        }
                        .foregroundColor(.black)
                    }
                    
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        LazyHStack {
                            ForEach(data.tours, id: \.self) { tour in
                                TourCardView(tour: tour)
                            }
                        }
                    }
                }
                .padding(EdgeInsets(top: 36, leading: 24, bottom: 50, trailing: 24))
            }
        }
        .interactiveDismissDisabled(true) // prevents swipe-down
        .presentationDetents([.medium, .custom(MyCustomDetent.self)])
        .presentationCornerRadius(36)
        .presentationBackgroundInteraction(.enabled)
        .presentationBackground(.thinMaterial)
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
