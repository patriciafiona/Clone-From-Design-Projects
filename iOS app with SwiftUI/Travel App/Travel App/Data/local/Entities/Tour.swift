//
//  Tour.swift
//  Travel App
//
//  Created by Patricia Fiona on 01/07/25.
//

import Foundation

struct Tour: Hashable {
  var id = UUID()
  var title: String
  var image: String
  var totalDays: Int
  var price: Double
  var rating: Double
}
