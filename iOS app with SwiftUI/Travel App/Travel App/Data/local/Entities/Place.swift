//
//  Place.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import Foundation
import MapKit

struct Place: Identifiable, Equatable {
    var id: String
    var name: String
    var image: String
    var rating: Double
    var decription: String
    var country: String //ISO 3166, Aplha-3
    var continent: String
    var tours: [Tour]
    var location: Location
//    var reviews: [Review]
}
