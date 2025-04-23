//
//  TravelDatabase.swift
//  Travel App
//
//  Created by FIONA Patricia on 23/04/25.
//

import Foundation

struct TravelDatabase {
    var allTravels: [Place] = [
        Place(
            name: "Borobudur Temple",
            image: "https://www.google.com/url?sa=i&url=https%3A%2F%2Fapp.pandooin.com%2Fid%2Fattraction%2Fcandi-borobudur-2&psig=AOvVaw3OHxhrmKidfyYudylxvW42&ust=1745486032115000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIDUk6no7YwDFQAAAAAdAAAAABAE",
            rating: 4.5,
            decription: "The Sailendra Dynasty constructed the world’s largest Buddhist monument between 780 and 840 AD. This dynasty was the ruling power at the time. The monument was built as a place of Buddhist worship and pilgrimage. It contains instructions for people to distance themselves from worldly desires and to pursue enlightenment and wisdom according to Buddhist teachings. This relic was discovered by the British forces in 1814 under the leadership of Sir Thomas Stamford Raffles. The temple area was completely cleared in 1835.\n\nBorobudur was built in the Mandala style, reflecting the universe in Buddhist beliefs. The structure is square with four entrances and a central point that is circular. As viewed from the outside inward, it is divided into two parts: the worldly realm, which is segmented into three zones on the outer part, and the Nirvana realm in the center.",
            country: "IDN",
            continent: "Asia"
        ),
        Place(
            name: "Nusa Peninda",
            image: "https://i0.wp.com/lovehardtraveloften.com/wp-content/uploads/2019/10/nusa-penida-things-to-do.jpg?fit=821%2C547&ssl=1",
            rating: 4.8,
            decription: "Nusa Penida is an island (= nusa) part of Klungkung Regency, Bali, Indonesia located in the southeast of Bali separated by the Badung Strait. Near this island there are also other small islands, namely Nusa Ceningan and Nusa Lembongan. The waters of Nusa Penida Island are famous for their diving areas, including Crystal Bay, Manta Point, Batu Meling, Batu Lumbung, Batu Abah, Toyapakeh and Malibu Point.",
            country: "IDN",
            continent: "Asia"
        ),
        Place(
            name: "Komodo National Park",
            image: "https://www.google.com/url?sa=i&url=https%3A%2F%2Ftravel.kompas.com%2Fread%2F2022%2F07%2F03%2F134542127%2F9-pulau-di-taman-nasional-komodo-kunjungi-saat-liburan-ke-sana%3Fpage%3Dall&psig=AOvVaw3fAEQf8lEzNHKFBdTvJ2E0&ust=1745486599750000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCKi39bfq7YwDFQAAAAAdAAAAABAE",
            rating: 4.7,
            decription: "Komodo National Park is one of the first national parks in Indonesia that is a natural habitat for two key animals, the Komodo monitor lizard (Varanus komodoensis) and the Yellow-crested Cockatoo (Cacatua Sulphurea) located in the East Nusa Tenggara hemisphere of Indonesia.\n\nKomodo National Park is an area consisting of several islands with sea waters that are administratively included in the West Manggarai Regency. There are at least 142 small islands with five main islands as habitats for the Komodo monitor lizard in Komodo National Park. The total area of ​​Komodo National Park (TNK) is 173,300 Ha with a water area reaching 114,801 Ha and a land area reaching 58,499 Ha.",
            country: "IDN",
            continent: "Asia"
        ),
    ]
}
