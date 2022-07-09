
import 'package:plant_app/model/PlantItem.dart';
import 'package:plant_app/model/PlantLocation.dart';
import 'package:plant_app/model/SizeType.dart';

class DataDummy{

  var categories = {
    "All",
    "Indoor",
    "Outdoor",
    "Popular"
  };

  List<PlantItem> plantsList = [
    PlantItem(
      id: 1,
      name: 'Agave s.',
      description: "Agave shawii is a species of monocarpic succulent plant in the genus Agave, commonly known as Shaw's agave. It is a rosette-forming plant characterized by glossy, green leaves with toothed margins. After several years of slow growth, the plant "
          "puts all of its resources to produce a towering stalk of flowers, and then dies. The death of the flowering rosette is compensated by the growth of numerous clonal pups. This species is segregated into two subspecies, one native to the coast of southwestern "
          "California and northwestern Baja California, known commonly as the coast agave, and another native to the Baja California Desert, known as the Goldman agave.",
      height: 10.5,
      rating: 4.6,
      humidity: 83,
      size: SizeType.Small,
      price: 39.0,
      location: PlantLocation.Outdoor,
      imagePath: "assets/images/plant_01.png"
    ),
    PlantItem(
        id: 2,
        name: 'Strelitzia n.',
        description: "Strelitzia nicolai, commonly known as the wild banana or giant white bird of paradise, is a species of banana-like plants with erect woody stems reaching a height of 7–8 m (23–26 ft), and the clumps formed can spread as far as 3.5 m (11 ft)."
            "\nThe 1.8 m (5 ft 11 in)-long leaves are grey-green and arranged like a fan at the top of the stems, similar to Ravenala madagascariensis. The inflorescence is composed of a dark blue bract, white sepals and a bluish-purple \"tongue\". The entire flower "
            "can be as much as 18 cm (7.1 in) high by 45 cm (18 in) long, and is typically held just above the point where the leaf fan emerges from the stem. Flowers are followed by triangular seed capsules."
            "\nStrelitzia nicolai is among the few plants which have been verified to contain the pigment bilirubin, which is usually found in animals.",
        height: 23.9,
        rating: 4.8,
        humidity: 71,
        size: SizeType.Medium,
        price: 63.99,
        location: PlantLocation.Indoor,
        imagePath: "assets/images/plant_02.png"
    ),
    PlantItem(
        id: 3,
        name: 'Dracaena r.',
        description: "Singing from India (Dracaena reflexa) is an ornamental plant species of suji plant originating "
            "from the Indian Ocean (Mozambique, Madagascar, and Mauritius).",
        height: 58.3,
        rating: 4.2,
        humidity: 85,
        size: SizeType.Small,
        price: 26.3,
        location: PlantLocation.Indoor,
        imagePath: "assets/images/plant_03.png"
    ),
    PlantItem(
        id: 4,
        name: 'Monstera d.',
        description: "Monstera deliciosa, the Swiss cheese plant[2] or split-leaf philodendron is a species of flowering plant native to tropical forests of southern Mexico, south to Panama."
            " It has been introduced to many tropical areas, and has become a mildly invasive species in Hawaii, Seychelles, Ascension Island and the Society Islands. It is very widely grown in temperate zones as a houseplant."
            "\nThe common name \"Swiss cheese plant\" is also used for the related species from the same genus, Monstera adansonii.[5] The common name \"split-leaf philodendron\" is also used for the species Thaumatophyllum "
            "bipinnatifidum, although neither species are in the genus Philodendron.[3]",
        height: 60.8,
        rating: 4.9,
        humidity: 77,
        size: SizeType.Medium,
        price: 99.8,
        location: PlantLocation.Indoor,
        imagePath: "assets/images/plant_04.png"
    ),
    PlantItem(
        id: 5,
        name: 'Sansevieria',
        description: "Sansevieria is a historically recognized genus of flowering plants, native to Africa, notably Madagascar, and southern Asia, "
            "now included in the genus Dracaena on the basis of molecular phylogenetic studies.Common names for the 70 or so species formerly placed "
            "in the genus include mother-in-law's tongue, devil's tongue, jinn's tongue, bow string hemp, snake plant and snake tongue. In the APG III "
            "classification system, Dracaena is placed in the family Asparagaceae, subfamily Nolinoideae (formerly the family Ruscaceae)."
            " It has also been placed in the former family Dracaenaceae.",
        height: 10.5,
        rating: 4.8,
        humidity: 92,
        size: SizeType.Medium,
        price: 12.7,
        location: PlantLocation.Indoor,
        imagePath: "assets/images/plant_05.png"
    ),
    PlantItem(
        id: 6,
        name: 'Epipremnum a.',
        description: "Epipremnum aureum is a species in the arum family Araceae, native to Mo'orea in the Society Islands of French Polynesia. "
            "The species is a popular houseplant in temperate regions but has also become naturalised in tropical and sub-tropical forests worldwide, "
            "including northern South Africa,[3] Australia, Southeast Asia, South Asia, the Pacific Islands and the West Indies, where it has caused "
            "severe ecological damage in some cases."
            "\nThe plant has a number of common names including golden pothos, Ceylon creeper, hunter's robe, ivy arum, house plant, money plant, "
            "silver vine, Solomon Islands ivy, marble queen, and taro vine. It is also called devil's vine or devil's ivy because it is almost "
            "impossible to kill and it stays green even when kept in the dark. It is sometimes mistakenly labeled as a Philodendron, Pothos or "
            "Scindapsus in plant stores. It is commonly known as a money plant in many parts of the Indian subcontinent. "
            "It rarely flowers without artificial hormone supplements; the last known spontaneous flowering in cultivation was reported in 1964.",
        height: 10.5,
        rating: 4.4,
        humidity: 83,
        size: SizeType.Small,
        price: 39.0,
        location: PlantLocation.Indoor,
        imagePath: "assets/images/plant_06.png"
    ),
    PlantItem(
        id: 7,
        name: 'Epipremnum a.',
        description: "Epipremnum aureum is a species in the arum family Araceae, native to Mo'orea in the Society Islands of French Polynesia. "
            "The species is a popular houseplant in temperate regions but has also become naturalised in tropical and sub-tropical forests worldwide, "
            "including northern South Africa,[3] Australia, Southeast Asia, South Asia, the Pacific Islands and the West Indies, where it has caused "
            "severe ecological damage in some cases."
            "\nThe plant has a number of common names including golden pothos, Ceylon creeper, hunter's robe, ivy arum, house plant, money plant, "
            "silver vine, Solomon Islands ivy, marble queen, and taro vine. It is also called devil's vine or devil's ivy because it is almost "
            "impossible to kill and it stays green even when kept in the dark. It is sometimes mistakenly labeled as a Philodendron, Pothos or "
            "Scindapsus in plant stores. It is commonly known as a money plant in many parts of the Indian subcontinent. "
            "It rarely flowers without artificial hormone supplements; the last known spontaneous flowering in cultivation was reported in 1964.",
        height: 42.9,
        rating: 4.7,
        humidity: 68,
        size: SizeType.Big,
        price: 11.2,
        location: PlantLocation.Indoor,
        imagePath: "assets/images/plant_07.png"
    ),
    PlantItem(
        id: 8,
        name: 'Dieffenbachia s.',
        description: "Dieffenbachia seguine, also known as dumbcane, or tuftroot, is a species of Dieffenbachia "
            "native to the tropical Americas—from southern Mexico, through Central America, to northern South America and "
            "Brazil. It is also native to several Caribbean islands, including Puerto Rico.[1]",
        height: 13.0,
        rating: 4.3,
        humidity: 59,
        size: SizeType.Medium,
        price: 4.4,
        location: PlantLocation.Outdoor,
        imagePath: "assets/images/plant_08.png"
    ),
    PlantItem(
        id: 9,
        name: 'Ananas c.',
        description: "The pineapple (Ananas comosus) is a tropical plant with an edible fruit; it is the most economically significant plant in the family Bromeliaceae."
            " The pineapple is indigenous to South America, where it has been cultivated for many centuries. The introduction of the pineapple to Europe in the 17th "
            "century made it a significant cultural icon of luxury. Since the 1820s, pineapple has been commercially grown in greenhouses and many tropical plantations."
            "\nPineapples grow as a small shrub; the individual flowers of the unpollinated plant fuse to form a multiple fruit. The plant is normally propagated from "
            "the offset produced at the top of the fruit,[2][5] or from a side shoot, and typically mature within a year.",
        height: 9.7,
        rating: 4.1,
        humidity: 67,
        size: SizeType.Small,
        price: 14.6,
        location: PlantLocation.Indoor,
        imagePath: "assets/images/plant_09.png"
    ),
    PlantItem(
        id: 10,
        name: 'Agave p.',
        description: "Agave parrasana, the cabbage head agave or cabbage head century plant, is a flowering plant in the family Asparagaceae. "
  "A slow-growing evergreen succulent from North East Mexico, it produces a compact rosette of fleshy thorn-tipped grey-green leaves, 60 cm tall "
  "and wide. The leaves are blue green and the thorns are red. The whole plant may reach 100 centimeters tall and wide.[5] Occasionally mature "
  "plants produce a spectacular flower head up to 6m tall, opening red and turning yellow.[6] This signals the death of the flowering rosette, "
  "however offsets may form and continue growing."
            "\nAs it can tolerate temperatures of −12 °C (10 °F) or less, it is a popular plant to grow outdoors in a sheltered cactus garden or "
            "similar environment, and has gained the Royal Horticultural Society's Award of Garden Merit. In the US, it may be grown outdoors in USDA "
            "hardiness zones 7–10. It is susceptible to scale and chlorosis resulting from magnesium deficiency.",
        height: 20.1,
        rating: 4.8,
        humidity: 42,
        size: SizeType.Small,
        price: 30.8,
        location: PlantLocation.Outdoor,
        imagePath: "assets/images/plant_10.png"
    ),
  ];

}