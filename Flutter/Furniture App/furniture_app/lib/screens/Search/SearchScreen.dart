import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http_status/http_status.dart';

import '../../model/api/RestClient.dart';
import '../../model/entity/FurnituesResponse.dart';
import '../../model/entity/FurniturItemResponse.dart';
import '../../utils/Constants.dart';
import '../../widget/FurnitureGridItem.dart';
import '../ProductDetails/ProductDetailScreen.dart';

class SearchScreen extends StatefulWidget {
  const SearchScreen({super.key});

  @override
  State<SearchScreen> createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  //Furniture data from API
  List<FurniturItemResponse> allData = [];
  List<FurniturItemResponse> furnitureList = [];

  void getFurnituresfromApi() async {
    RestClient.getFurnitures().then((response) {
      setState(() {
        if (response.statusCode == HttpStatusCode.ok) { // res.statusCode == 200
          Map<String, dynamic> mapOfFurnitures = json.decode(response.body);
          var furnituresResponse = FurnituresResponse.fromJson(mapOfFurnitures);
          allData = furnituresResponse.results!;
          logger.i("SUCCESS GET FURNITURE DATA: $allData");
        }
      });
    });
  }

  void getFurnituresfromApiByName(String productName) async {
    RestClient.getFurnitures().then((response) {
      setState(() {
        if (allData.isNotEmpty) { // res.statusCode == 200
          if(productName.isEmpty){
            furnitureList = allData;
          }else{
            furnitureList = allData.where((list) => list.name!.toLowerCase().contains(productName.toLowerCase())).toList();
          }
        }
      });
    });
  }

  @override
  void initState() {
    super.initState();
    getFurnituresfromApi();
  }

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.sizeOf(context).width;
    double screenHeight = MediaQuery.sizeOf(context).height;

    var size = MediaQuery.of(context).size;

    final double itemHeight = (size.height - kToolbarHeight - 24) / 3;
    final double itemWidth = size.width / 2;

    return Scaffold(
        resizeToAvoidBottomInset: false,
        body: Container(
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage("assets/image/background.png"),
                fit: BoxFit.cover,
              ),
            ),
            padding: const EdgeInsets.only(left: 16, right: 16, bottom: 32, top: 48),
            child: Column(
              children: [
                topSection(context),

                const SizedBox(height: 8),

                Container(
                  padding: const EdgeInsets.all(8),
                  decoration: BoxDecoration(
                      color: transparentGray,
                      borderRadius: const BorderRadius.all(Radius.circular(50))
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(left: 8.0),
                        child: IconButton(
                            onPressed: () => {},
                            icon: const Icon(
                              Icons.search,
                              color: Colors.white,
                              size: 24.0,
                            )
                        ),
                      ),
                      Expanded(
                          child: TextField(
                            onChanged: (text) {
                              getFurnituresfromApiByName(text);
                            },
                            cursorColor: Colors.white60,
                            decoration: const InputDecoration.collapsed(
                              hintText: 'Enter a product name',
                              hintStyle: TextStyle(
                                color: Colors.white38,
                                fontFamily: "Lufga",
                                fontSize: 14,
                              )
                            ),
                            style: const TextStyle(
                                color: Colors.white,
                                fontFamily: "Lufga",
                                fontSize: 14,
                            ),
                          ),
                      )
                    ],
                  ),
                ),

                const SizedBox(height: 16),

                SizedBox(
                    height: screenHeight * .7,
                    child: () {
                      if (allData.isEmpty) {
                        return const Center(
                          child: CircularProgressIndicator(),
                        );
                      }else{
                        if(furnitureList.isEmpty){
                          return GridView.builder(
                            controller: ScrollController(keepScrollOffset: false),
                            shrinkWrap: true,
                            scrollDirection: Axis.vertical,
                            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                              crossAxisCount: 2, // number of items in each row
                              mainAxisSpacing: 4.0, // spacing between rows
                              crossAxisSpacing: 2.0, // spacing between columns,
                              childAspectRatio: (itemWidth / itemHeight),
                            ),
                            itemCount: allData.length, // total number of items
                            itemBuilder: (context, index) {
                              return FurnitureGridItem(
                                  furniture: allData[index],
                                  itemWidth: itemWidth
                              );
                            },
                          );
                        }else{
                          return GridView.builder(
                            controller: ScrollController(keepScrollOffset: false),
                            shrinkWrap: true,
                            scrollDirection: Axis.vertical,
                            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                              crossAxisCount: 2, // number of items in each row
                              mainAxisSpacing: 4.0, // spacing between rows
                              crossAxisSpacing: 2.0, // spacing between columns,
                              childAspectRatio: (itemWidth / itemHeight),
                            ),
                            itemCount: furnitureList.length, // total number of items
                            itemBuilder: (context, index) {
                              return FurnitureGridItem(
                                  furniture: furnitureList[index],
                                  itemWidth: itemWidth
                              );
                            },
                          );
                        }
                      }
                    }
                  ())
              ],
            )
        )
    );
  }

  Row topSection(BuildContext context) {
    return Row(
      children: [
        ElevatedButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          style: ElevatedButton.styleFrom(
            shape: const CircleBorder(),
            padding: const EdgeInsets.all(8),
            backgroundColor: transparentGray, // <-- Button color
            foregroundColor: Colors.black, // <-- Splash color
          ),
          child: const Icon(
            Icons.arrow_back,
            size: 20.0,
            color: Colors.white,
          ),
        ),
        const Expanded(
          flex: 1,
            child: Text(
              "Search Product",
              style: TextStyle(
                color: Colors.white,
                fontSize: 16,
                fontFamily: "Lufga",
              ),

            )
        ),
      ],
    );
  }
}
