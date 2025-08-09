//
//  DataModel.swift
//  Travel App
//
//  Created by FIONA Patricia on 02/07/25.
//

//  DataModel.swift
import Foundation
import SwiftData

// Adding @Model here tells SwiftData that this will be a table in our database
@Model
class FavoritePlaceModel {
    // We can add attributes to the fields of our database using the @Attribute property wrapper
    // the .unique option here tells our database that this column is guaranteed to be unique
    @Attribute(.unique) var id = UUID()
    var placeId: String
    
    init(placeId: String) {
        self.placeId = placeId
    }
}
