//
//  TravelDatabase.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import Foundation

public struct TravelDatabase {
    var allTravels: [Place] = [
        Place(
            name: "Borobudur Temple",
            image: "https://cdn1-production-images-kly.akamaized.net/qwQsZXIsxNsjJaO6i8XJzrF1st0=/1200x900/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3023951/original/083764400_1579164554-indonesia-1098328_1920.jpg",
            rating: 4.5,
            decription: "The Sailendra Dynasty constructed the world’s largest Buddhist monument between 780 and 840 AD. This dynasty was the ruling power at the time. The monument was built as a place of Buddhist worship and pilgrimage. It contains instructions for people to distance themselves from worldly desires and to pursue enlightenment and wisdom according to Buddhist teachings. This relic was discovered by the British forces in 1814 under the leadership of Sir Thomas Stamford Raffles. The temple area was completely cleared in 1835.\n\nBorobudur was built in the Mandala style, reflecting the universe in Buddhist beliefs. The structure is square with four entrances and a central point that is circular. As viewed from the outside inward, it is divided into two parts: the worldly realm, which is segmented into three zones on the outer part, and the Nirvana realm in the center.",
            country: "IDN",
            continent: "Asia"
        ),
        Place(
            name: "Nusa Peninda",
            image: "https://upload.wikimedia.org/wikipedia/commons/d/d4/Kelingking_Beach_%28T-Rex_Bay%29_of_Nusa_Penida%2C_Bali_%282025%29_-_img_11.jpg",
            rating: 4.8,
            decription: "Nusa Penida is an island (= nusa) part of Klungkung Regency, Bali, Indonesia located in the southeast of Bali separated by the Badung Strait. Near this island there are also other small islands, namely Nusa Ceningan and Nusa Lembongan. The waters of Nusa Penida Island are famous for their diving areas, including Crystal Bay, Manta Point, Batu Meling, Batu Lumbung, Batu Abah, Toyapakeh and Malibu Point.",
            country: "IDN",
            continent: "Asia"
        ),
        Place(
            name: "Komodo National Park",
            image: "https://indonesia.travel/content/dam/indtravelrevamp/en/news-events/news/10-reasons-to-visit-the-magnificent-komodo-national-park/dcd837c723d980d06b0b75b094d316c80bfde1a9-6727f.jpg",
            rating: 4.7,
            decription: "Komodo National Park is one of the first national parks in Indonesia that is a natural habitat for two key animals, the Komodo monitor lizard (Varanus komodoensis) and the Yellow-crested Cockatoo (Cacatua Sulphurea) located in the East Nusa Tenggara hemisphere of Indonesia.\n\nKomodo National Park is an area consisting of several islands with sea waters that are administratively included in the West Manggarai Regency. There are at least 142 small islands with five main islands as habitats for the Komodo monitor lizard in Komodo National Park. The total area of ​​Komodo National Park (TNK) is 173,300 Ha with a water area reaching 114,801 Ha and a land area reaching 58,499 Ha.",
            country: "IDN",
            continent: "Asia"
        ),
    ]
    
    func getPlacesByContinent(selectedContinent: String)-> [CardView.Model] {
        let filteredData = allTravels.filter {
            $0.continent.lowercased() == selectedContinent.lowercased()
        }
        var cards: [CardView.Model] = []
        
        for data in filteredData {
            cards.append(
                CardView.Model(
                    place: data
                )
            )
        }
        
        return cards
    }
}
