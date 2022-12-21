import 'package:flutter/material.dart';
import 'package:nft_marketplace/model/entities/Collection.dart';
import 'package:nft_marketplace/model/entities/NFT.dart';
import 'entities/Creator.dart';

class DataSource {
  var creators = [
    Creator(
        id: "cr_00001_102022",
        name: "YuGi-Labs",
        isVerified: true,
        joinDate: "October 2022",
        description: "¥u-Gi-Labs is a team of successful 3D CGI artists, "
            "art directors, creative directors, engineers, designers, and "
            "musicians developing metaver",
        collections: [
         Collection(
             id: "co_00001_102022_01",
             name: "YuGiYn",
             totalItems: 8888,
             created: 'October 2022',
             creatorFee: 7.5,
             category: "Virtual worlds",
             description: "Set inside the skyscraper called “¥u-Gi-¥n” "
                 "located in city of Shibuya in the near future, ¥u-Gi-¥n "
                 "is a virtual world building project that provides "
                 "entertainment such as games, manga, anime, fashion "
                 "and music. There are 4 districts in the virtual world "
                 "and players can go back and forth between different "
                 "districts and interact with each other. We are building "
                 "a digital economic zone through TOKYO CULTURE."
         )
       ]
    ),
    Creator(
        id: "cr_00002_102021",
        name: "GutterLabsLLC",
        isVerified: true,
        joinDate: "October 2021",
        description: "Welcome to the Gutter.",
        collections: [
          Collection(
              id: "co_00002_102021_01",
              name: "Gutter Dogs",
              totalItems: 2953,
              created: 'October 2021',
              creatorFee: 5.0,
              category: "PFPs",
              description: "Welcome to the Gutter. The Gutter Dogs are a collection "
                  "of 3,000 randomly generated NFTs on the Ethereum blockchain that "
                  "double as membership tokens into the Gutter Cat Gang. Gutter "
                  "Dogs represent GCG’s base level of membership, unlocking "
                  "varying and unique levels of community led access and perks."
          ),
          Collection(
              id: "co_00002_102021_02",
              name: "Gutter Rats",
              totalItems: 2998,
              created: 'July 2021',
              creatorFee: 5.0,
              category: "PFPs",
              description: "Welcome to the Gutter. The Gutter Rats are a collection "
                  "of 3,000 randomly generated NFTs on the Ethereum blockchain that "
                  "double as membership tokens into the Gutter Cat Gang. Gutter Rats "
                  "represent GCG’s base level of membership, unlocking varying and "
                  "unique levels of community led access and perks."
          ),
          Collection(
              id: "co_00002_102021_03",
              name: "Gutter Cat Gang",
              totalItems: 3000,
              created: 'June 2021',
              creatorFee: 5.0,
              category: "PFPs",
              description: "Welcome to the Gutter. The Gutter Cats are a collection "
                  "of 3,000 randomly generated NFTs on the Ethereum blockchain that "
                  "double as membership tokens into the Gutter Cat Gang. Gutter Cats "
                  "represent GCG’s premier level of membership, unlocking varying and "
                  "unique levels of community led access and perks."
          ),
        ]
    ),
    Creator(
        id: "cr_00003_112021",
        name: "EliteApeCEO",
        isVerified: false,
        joinDate: "November 2021",
        description: "Founder of Elite Ape Billionaire Community.",
        collections: [
          Collection(
              id: "co_00003_112021_01",
              name: "Elite Ape Billionaire Community",
              totalItems: 40384,
              created: 'October 2021',
              creatorFee: 5.0,
              category: "PFPs",
              description: "The Elite Ape Billionaire Community is more than a project, "
                  "it’s the way you see the reality. The real transformation of yourself. "
                  "The Elite Ape NFTs can be your transformational access to utilities and "
                  "perks through a community of like-minded people with the same primary "
                  "goal: to the moon. Visit our website for more details on the project."
          )
        ]
    )
  ];

  var allNft = [
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #4477",
        price: 2.0,
        lastSale: 0.1401 ,
        totalViews: 417,
        totalFavorites: 7,
        imagePath: "YuGiYn #4477.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_01",
        name: "Gutter Dog #209",
        price: 0.499,
        lastSale: 0.455 ,
        totalViews: 616,
        totalFavorites: 13,
        imagePath: "Gutter_Dog_#209.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #6678",
        price: 0.2006,
        lastSale: 0.2006 ,
        totalViews: 1400,
        totalFavorites: 16,
        imagePath: "YuGiYn_#6678.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10003",
        price: 0.009,
        lastSale: 0.0034 ,
        totalViews: 12,
        totalFavorites: 0,
        imagePath: "EliteApe_#10003.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_02",
        name: "Gutter Rat #214",
        price: 0.55,
        lastSale: 0.34 ,
        totalViews: 1100,
        totalFavorites: 16,
        imagePath: "Gutter_Rat_#214.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #1101",
        price: 0.169, //ETH
        lastSale: 0.1373, //WETH
        totalViews: 238,
        totalFavorites: 2,
        imagePath: "YuGiYn_#1101.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_01",
        name: "Gutter Dog #2934",
        price: 0.55,
        lastSale: 0.455 ,
        totalViews: 695,
        totalFavorites: 5,
        imagePath: "Gutter_Dog_#2934.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10006",
        price: 0.009,
        lastSale: 0.0021 ,
        totalViews: 12,
        totalFavorites: 0,
        imagePath: "EliteApe_#10006.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #930",
        price: 0.17,
        lastSale: 0.430,
        totalViews: 119,
        totalFavorites: 2,
        imagePath: "YuGiYn_#930.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_03",
        name: "Gutter Cat #1486",
        price: 2.69,
        lastSale: 2.15 ,
        totalViews: 873,
        totalFavorites: 23,
        imagePath: "Gutter_Cat_#1486.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #6009",
        price: 8.888,
        lastSale: 0.1403 ,
        totalViews: 267,
        totalFavorites: 10,
        imagePath: "YuGiYn_#6009.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_03",
        name: "Gutter Cat #660",
        price: 2.745,
        lastSale: 2.55 ,
        totalViews: 1500,
        totalFavorites: 31,
        imagePath: "Gutter_Cat_#660.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10019",
        price: 0.009,
        lastSale: 0.0019 ,
        totalViews: 9,
        totalFavorites: 2,
        imagePath: "EliteApe_#10019.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #5745",
        price: 2.88,
        lastSale: 0.1401 ,
        totalViews: 324,
        totalFavorites: 11,
        imagePath: "YuGiYn_#5745.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_01",
        name: "Gutter Dog #2573",
        price: 0.69,
        lastSale: 0.455 ,
        totalViews: 87,
        totalFavorites: 4,
        imagePath: "Gutter_Dog_#2573.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #305",
        price: 0.1744,
        lastSale: 0.153,
        totalViews: 198,
        totalFavorites: 3,
        imagePath: "YuGiYn_#305.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_03",
        name: "Gutter Cat #962",
        price: 2.9,
        lastSale: 2.23 ,
        totalViews: 1200,
        totalFavorites: 11,
        imagePath: "Gutter_Cat_#962.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10018",
        price: 0.009,
        lastSale: 0.0019 ,
        totalViews: 5,
        totalFavorites: 0,
        imagePath: "EliteApe_#10018.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #4612",
        price: 0.185,
        lastSale: 0.200,
        totalViews: 594,
        totalFavorites: 4,
        imagePath: "YuGiYn_#4612.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_02",
        name: "Gutter Rat #1135",
        price: 0.48,
        lastSale: 0.359 ,
        totalViews: 274,
        totalFavorites: 6,
        imagePath: "Gutter_Rat_#1135.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10026",
        price: 0.009,
        lastSale: 0.0019 ,
        totalViews: 3,
        totalFavorites: 1,
        imagePath: "EliteApe_#10026.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #228",
        price: 0.188,
        lastSale: 0.155,
        totalViews: 73,
        totalFavorites: 1,
        imagePath: "YuGiYn_#228.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_02",
        name: "Gutter Rat #475",
        price: 0.5,
        lastSale: 0.34 ,
        totalViews: 908,
        totalFavorites: 13,
        imagePath: "Gutter_Rat_#475.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #8616",
        price: 0.1889,
        lastSale: 0.275,
        totalViews: 56,
        totalFavorites: 1,
        imagePath: "YuGiYn_#8616.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_03",
        name: "Gutter Cat #2578",
        price: 3.2,
        lastSale: 2.93 ,
        totalViews: 413,
        totalFavorites: 5,
        imagePath: "Gutter_Cat_#2578.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #7436",
        price: 0.189,
        lastSale: 0.17,
        totalViews: 167,
        totalFavorites: 2,
        imagePath: "YuGiYn_#7436.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_03",
        name: "Gutter Cat #620",
        price: 3.25,
        lastSale: 2.15 ,
        totalViews: 334,
        totalFavorites: 17,
        imagePath: "Gutter_Cat_#620.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #344",
        price: 0.19,
        lastSale: 0.161,
        totalViews: 65,
        totalFavorites: 1,
        imagePath: "YuGiYn_#344.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_02",
        name: "Gutter Rat #2651",
        price: 0.53,
        lastSale: 0.34 ,
        totalViews: 983,
        totalFavorites: 6,
        imagePath: "Gutter_Rat_#2651.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #4554",
        price: 0.3027,
        lastSale: 0.329,
        totalViews: 43,
        totalFavorites: 1,
        imagePath: "YuGiYn_#4554.png"
    ),
    NFT(
        creatorId: "cr_00002_102021",
        collectionId: "co_00002_102021_03",
        name: "Gutter Cat #987",
        price: 3.7,
        lastSale: 3.46 ,
        totalViews: 122,
        totalFavorites: 2,
        imagePath: "Gutter Cat #987.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #5164",
        price: 2.0,
        lastSale: 0.1401 ,
        totalViews: 140,
        totalFavorites: 2,
        imagePath: "YuGiYn_#5164.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10035",
        price: 0.009,
        lastSale: 0.0019 ,
        totalViews: 2,
        totalFavorites: 0,
        imagePath: "EliteApe_#10035.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #8271",
        price: 0.23,
        lastSale: 0.190,
        totalViews: 289,
        totalFavorites: 9,
        imagePath: "YuGiYn_#8271.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10017",
        price: 0.009,
        lastSale: 0.0019 ,
        totalViews: 8,
        totalFavorites: 0,
        imagePath: "EliteApe_#10017.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #965",
        price: 0.35,
        lastSale: 0.138,
        totalViews: 73,
        totalFavorites: 1,
        imagePath: "YuGiYn_#965.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #8704",
        price: 0.45,
        lastSale: 0.138,
        totalViews: 174,
        totalFavorites: 4,
        imagePath: "YuGiYn_#8704.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #8311",
        price: 17.178,
        lastSale: 0.2506,
        totalViews: 627,
        totalFavorites: 5,
        imagePath: "YuGiYn_#8311.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #1001",
        price: 0.009,
        lastSale: 0.0019 ,
        totalViews: 5,
        totalFavorites: 0,
        imagePath: "EliteApe_#1001.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #698",
        price: 11.11,
        lastSale: 0.2505,
        totalViews: 584,
        totalFavorites: 11,
        imagePath: "YuGiYn_#698.png"
    ),
    NFT(
        creatorId: "cr_00003_112021",
        collectionId: "co_00003_112021_01",
        name: "Elite Ape #10002",
        price: 0.009,
        lastSale: 0.007 ,
        totalViews: 16,
        totalFavorites: 1,
        imagePath: "EliteApe_#10002.png"
    ),
    NFT(
        creatorId: "cr_00001_102022",
        collectionId: "co_00001_102022_01",
        name: "YuGiYn #5533",
        price: 7.2111,
        lastSale: 0.2035 ,
        totalViews: 285,
        totalFavorites: 5,
        imagePath: "YuGiYn_#5533.png"
    ),
  ];
}