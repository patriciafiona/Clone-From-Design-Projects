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
            continent: "Asia",
            tours: [
                Tour(
                    title: "Amazing city of Java",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/shutterstock_1262182054.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 5,
                    price: 192.18,
                    rating: 4.7
                ),
                Tour(
                    title: "The wonderful world of magical temples",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/Screenshot-2024-12-14-at-00.49.51-1024x710.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 8,
                    price: 333.33,
                    rating: 4.8
                ),
                Tour(
                    title: "Bali best places",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/shutterstock_2043979748.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 6,
                    price: 432.10,
                    rating: 4.9
                ),
            ]
        ),
        Place(
            name: "Nusa Peninda",
            image: "https://upload.wikimedia.org/wikipedia/commons/d/d4/Kelingking_Beach_%28T-Rex_Bay%29_of_Nusa_Penida%2C_Bali_%282025%29_-_img_11.jpg",
            rating: 4.8,
            decription: "Nusa Penida is an island (= nusa) part of Klungkung Regency, Bali, Indonesia located in the southeast of Bali separated by the Badung Strait. Near this island there are also other small islands, namely Nusa Ceningan and Nusa Lembongan. The waters of Nusa Penida Island are famous for their diving areas, including Crystal Bay, Manta Point, Batu Meling, Batu Lumbung, Batu Abah, Toyapakeh and Malibu Point.",
            country: "IDN",
            continent: "Asia",
            tours: [
                Tour(
                    title: "Amazing city of Java",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/shutterstock_1262182054.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 5,
                    price: 192.18,
                    rating: 4.7
                ),
                Tour(
                    title: "The wonderful world of magical temples",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/Screenshot-2024-12-14-at-00.49.51-1024x710.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 8,
                    price: 333.33,
                    rating: 4.8
                ),
                Tour(
                    title: "Bali best places",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/shutterstock_2043979748.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 6,
                    price: 432.10,
                    rating: 4.9
                ),
            ]
        ),
        Place(
            name: "Komodo National Park",
            image: "https://indonesia.travel/content/dam/indtravelrevamp/en/news-events/news/10-reasons-to-visit-the-magnificent-komodo-national-park/dcd837c723d980d06b0b75b094d316c80bfde1a9-6727f.jpg",
            rating: 4.7,
            decription: "Komodo National Park is one of the first national parks in Indonesia that is a natural habitat for two key animals, the Komodo monitor lizard (Varanus komodoensis) and the Yellow-crested Cockatoo (Cacatua Sulphurea) located in the East Nusa Tenggara hemisphere of Indonesia.\n\nKomodo National Park is an area consisting of several islands with sea waters that are administratively included in the West Manggarai Regency. There are at least 142 small islands with five main islands as habitats for the Komodo monitor lizard in Komodo National Park. The total area of ​​Komodo National Park (TNK) is 173,300 Ha with a water area reaching 114,801 Ha and a land area reaching 58,499 Ha.",
            country: "IDN",
            continent: "Asia",
            tours: [
                Tour(
                    title: "Amazing city of Java",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/shutterstock_1262182054.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 5,
                    price: 192.18,
                    rating: 4.7
                ),
                Tour(
                    title: "The wonderful world of magical temples",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/Screenshot-2024-12-14-at-00.49.51-1024x710.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 8,
                    price: 333.33,
                    rating: 4.8
                ),
                Tour(
                    title: "Bali best places",
                    image: "https://ik.imagekit.io/tvlk/blog/2024/12/shutterstock_2043979748.jpg?tr=q-70,c-at_max,w-500,h-300,dpr-2",
                    totalDays: 6,
                    price: 432.10,
                    rating: 4.9
                ),
            ]
        ),
        Place(
            name: "Cristo Redentor",
            image: "https://i.content4travel.com/seeplaces/temp/9ecc4378-3201-4070-89e8-de66534c9d9c.jpg",
            rating: 4.6,
            decription: "Christ the Redeemer (Portuguese: Cristo Redentor, standard Brazilian Portuguese: [ˈkɾistu ʁedẽˈtoʁ]) is an Art Deco statue of Jesus in Rio de Janeiro, Brazil, created by French-Polish sculptor Paul Landowski and built by Brazilian engineer Heitor da Silva Costa, in collaboration with French engineer Albert Caquot. Romanian sculptor Gheorghe Leonida sculpted the face. Constructed between 1922 and 1931, the statue is 30 metres (98 ft) high, excluding its 8-metre (26 ft) pedestal. The arms stretch 28 metres (92 ft) wide. It is made of reinforced concrete and soapstone. Christ the Redeemer differs considerably from its original design, as the initial plan was a large Christ with a globe in one hand and a cross in the other. Although the project organizers originally accepted the design, it later changed to the statue of today, with the arms spread out wide.\n\nThe statue weighs 635 metric tons (625 long, 700 short tons), and is located at the peak of the 700-metre (2,300 ft) Corcovado mountain in the Tijuca National Park overlooking the city of Rio de Janeiro. This statue is the largest Art Deco–style sculpture in the world. A symbol of Christianity around the world, the statue has also become a cultural icon of both Rio de Janeiro and Brazil and was voted one of the New 7 Wonders of the World.",
            country: "BRA",
            continent: "America",
            tours: [
                Tour(
                    title: "Iconic Brazil",
                    image: "https://www.thesmoothescape.com/wp-content/uploads/2025/05/Fernando-de-Noronha-sunset-2.jpg",
                    totalDays: 8,
                    price: 659.3,
                    rating: 4.6
                ),
                Tour(
                    title: "Dream Paraty",
                    image: "https://www.thesmoothescape.com/wp-content/uploads/2025/05/Saco-do-Mamangua-Paraty.jpg",
                    totalDays: 5,
                    price: 427.18,
                    rating: 4.7
                )
            ]
        ),
        Place(
            name: "Statue of Liberty",
            image: "https://www.usatoday.com/gcdn/authoring/2019/05/16/USAT/9b4d37bc-a331-40c3-8373-c1c94ae706c4-Odaiba_Statue_of_Liberty_Replica_Japan_National_Tourism_Organization.jpg?crop=2000,2667,x1007,y0",
            rating: 4.4,
            decription: "The Statue of Liberty (Liberty Enlightening the World; French: La Liberté éclairant le monde) is a colossal neoclassical sculpture on Liberty Island in New York Harbor, within New York City. The copper-clad statue, a gift to the United States from the people of France, was designed by French sculptor Frédéric Auguste Bartholdi and its metal framework was built by Gustave Eiffel. The statue was dedicated on October 28, 1886.",
            country: "USA",
            continent: "America",
            tours: [
                Tour(
                    title: "Hollywood trip",
                    image: "https://id.hotels.com/go/usa/hollywood-sign-los-angeles",
                    totalDays: 7,
                    price: 511.98,
                    rating: 4.7
                ),
                Tour(
                    title: "Iconic Canyon",
                    image: "https://goguides.azureedge.net/media/xp4g1d21/cb7d62d3-0fd9-4d7e-af85-8ef47171d8e0.jpg?anchor=center&mode=crop&width=1600&height=1066&quality=50",
                    totalDays: 4,
                    price: 310,
                    rating: 4.8
                )
            ]
        ),
        Place(
            name: "Sydney Opera House",
            image: "https://upload.wikimedia.org/wikipedia/commons/a/a0/Sydney_Australia._%2821339175489%29.jpg",
            rating: 4.4,
            decription: "The Sydney Opera House in Sydney, New South Wales, Australia is one of the most distinctive and famous buildings of the 20th century. It is located on Bennelong Point in Sydney Harbour near the Sydney Harbour Bridge and the view of the two buildings has become an icon of Australia.\n\nFor the millions of tourists who visit, the building has an attraction in its shell-like shape. In addition to being a tourist attraction, the building also hosts various theater, ballet and other arts performances. The building is managed by the Opera House Trust and is the headquarters of Opera Australia, the Sydney Theatre Company and the Sydney Symphony Orchestra.\n\nThe design was obtained from a competition won by Jørn Utzon of Denmark in 1955. Utzon himself came to Sydney for supervision in 1957.\n\nThe building was also included in the UNESCO World Heritage Site list in 2007.",
            country: "AUS",
            continent: "Australia",
            tours: [
                Tour(
                    title: "Opera Day",
                    image: "https://lh7-rt.googleusercontent.com/docsz/AD_4nXdRdMs2nqCnM9Eddni_AD3Kty7wkvr3qQv5ZqWHJPXR997w6AFyjRZkukW4uRCFnKZ-ErBVFXdC9ezMMjJI4L5PAfS_z6mdIYqZRgtbyqNQpMkMa1WAX7gIBJzxspidBprJwtNx4w?key=zfED5S0I2JP8zhig5KGJigLT",
                    totalDays: 1,
                    price: 138.13,
                    rating: 4.5
                )
            ]
        ),
        Place(
            name: "Great Pyramid of Giza",
            image: "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Great_Pyramid_of_Giza_-_Pyramid_of_Khufu.jpg/1200px-Great_Pyramid_of_Giza_-_Pyramid_of_Khufu.jpg",
            rating: 4.6,
            decription: "The Great Pyramid of Giza is the largest Egyptian pyramid. It served as the tomb of pharaoh Khufu, who ruled during the Fourth Dynasty of the Old Kingdom. Built c. 2600 BC, over a period of about 26 years, the pyramid is the oldest of the Seven Wonders of the Ancient World, and the only wonder that has remained largely intact. It is the most famous monument of the Giza pyramid complex, which is part of the UNESCO World Heritage Site \"Memphis and its Necropolis\". It is situated at the northeastern end of the line of the three main pyramids at Giza.",
            country: "EGY",
            continent: "Africa",
            tours: [
                Tour(
                    title: "Full of History",
                    image: "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/30/1a/9f/6c/valley-og-the-kings.jpg?w=1000&h=800&s=1",
                    totalDays: 6,
                    price: 939.99,
                    rating: 4.5
                )
            ]
        ),
        Place(
            name: "The Colosseum",
            image: "https://cpanel-blog.smsperkasa.com/wp-content/uploads/2023/09/bagian-dalam-koloseum-1024x682.jpg",
            rating: 4.7,
            decription: "The Colosseum (Latin: Colosseum or Colisseum; Italian: Colosseo) is a historical relic in the form of a gladiatorial arena, built by Vespasian. The large elliptical performance venue is called an amphitheater or with its original name Amphitheatrum Flavium, which is included in one of the Sixty-Nine Wonders of the Medieval World. This site is located in the small city of Rome in Italy, which was founded by the Mayor Vespasian during the time of Domitian and completed by his son Titus, and became one of the greatest works of Roman Imperial architecture ever built. The Colosseum was designed to accommodate 50,000 spectators.",
            country: "ITA",
            continent: "Europe",
            tours: [
                Tour(
                    title: "All Famous of Landmark",
                    image: "https://www.insightvacations.com/wp-content/uploads/2025/02/michele-bitetto-1AQTOjczEB0-unsplash-980x653.jpg",
                    totalDays: 6,
                    price: 727.24,
                    rating: 4.7
                )
            ]
        ),
        Place(
            name: "Eiffel Tower",
            image: "https://media.architecturaldigest.com/photos/66a951edce728792a48166e6/3:2/w_7950,h_5300,c_limit/GettyImages-955441104.jpg",
            rating: 4.7,
            decription: "Named after its designer, Gustave Eiffel, the Eiffel Tower is the tallest building in Paris and one of the world's most recognizable structures. More than 200,000,000 people have visited the tower since its construction in 1889, including 6,719,200 in 2006, making it the world's most visited paid monument. Including its 24 m (79 ft) antenna, the structure has stood 325 m (1,063 ft) tall since 2000, which is the same as a conventional 81-story building.\n\nWhen the tower was completed in 1889, it was the tallest structure in the world—a title it held until 1930 when the Chrysler Building in New York City (319 m — 1,047 ft) was completed. The tower is currently the fifth tallest in France and the tallest in Paris, with the second tallest structure being the Tour Montparnasse (210 m — 689 ft), although it will be surpassed by the Tour AXA (225.11 m — 738.36 ft).",
            country: "FRA",
            continent: "Europe",
            tours: [
                Tour(
                    title: "Art Day in France",
                    image: "https://www.planetware.com/wpimages/2021/11/france-top-attractions-musee-du-louvre.jpg",
                    totalDays: 1,
                    price: 214.87,
                    rating: 4.6
                )
            ]
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
  
  func getPlacesByUuid(uuid: String) throws -> Place {
    let filteredData = allTravels.filter {
      $0.id.uuidString == uuid
    }.first
    
    return filteredData!
  }
}
