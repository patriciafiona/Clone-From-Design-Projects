import 'dart:convert';

import 'package:carousel_slider_plus/carousel_slider_plus.dart';
import 'package:chip_list/chip_list.dart';
import 'package:flutter/material.dart';
import 'package:furniture_app/model/entity/FurnituesResponse.dart';
import 'package:furniture_app/model/entity/FurniturItemResponse.dart';
import 'package:furniture_app/screens/ProductDetails/ProductDetailScreen.dart';
import 'package:http_status/http_status.dart';
import 'package:page_transition/page_transition.dart';

import '../../../model/DataDummy.dart';
import '../../../model/api/RestClient.dart';
import '../../../utils/Constants.dart';

class HomeTabScreen extends StatefulWidget {
  const HomeTabScreen({super.key});

  @override
  State<HomeTabScreen> createState() => _HomeTabScreenState();
}

class _HomeTabScreenState extends State<HomeTabScreen> {
  //Chips settings
  int chipTag = 0;
  String selectedCategoryChip = "all";

  //Carousel settings
  int _current = 0;
  final CarouselControllerPlus _controller = CarouselControllerPlus();

  //Furniture data from API
  List<FurniturItemResponse> furnitureList = [];

  void getFurnituresfromApi() async {
    RestClient.getFurnitures().then((response) {
      setState(() {
        if (response.statusCode == HttpStatusCode.ok) { // res.statusCode == 200
          Map<String, dynamic> mapOfFurnitures = json.decode(response.body);
          var furnituresResponse = FurnituresResponse.fromJson(mapOfFurnitures);
          furnitureList = furnituresResponse.results!;
          logger.i("SUCCESS GET FURNITURE DATA: $furnitureList");
        }
      });
    });
  }

  void getFurnituresfromApiWithFilteredCategory(String category) async {
    RestClient.getFurnitures().then((response) {
      setState(() {
        if (response.statusCode == HttpStatusCode.ok) { // res.statusCode == 200
          Map<String, dynamic> mapOfFurnitures = json.decode(response.body);
          var furnituresResponse = FurnituresResponse.fromJson(mapOfFurnitures);
          var res = furnituresResponse.results!;
          furnitureList = res.where((i) => i.category?.toLowerCase() == category).toList();
          logger.i("SUCCESS GET FURNITURE DATA: $furnitureList");
        }
      });
    });
  }

  @override
  void initState() {
    super.initState();
    getFurnituresfromApi();
  }

  Future<void> _pullRefresh() async {
    setState(() {
      furnitureList = [];
    });

    if(chipTag == 0){
      //show all
      getFurnituresfromApi();
    }else{
      //show by selected category name
      getFurnituresfromApiWithFilteredCategory(selectedCategoryChip);
    }
  }

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.sizeOf(context).width;
    double screenHeight = MediaQuery.sizeOf(context).height;

    //Banner settings
    final List<Widget> imageSliders = Datadummy().homeBannerImgList
        .map((item) => Container(
          margin: const EdgeInsets.all(3.0),
          child: ClipRRect(
              borderRadius: const BorderRadius.all(Radius.circular(5.0)),
              child: Stack(
                children: <Widget>[
                  Align(
                    alignment: Alignment.centerRight,
                    child: Image.network(
                      "https://raw.githubusercontent.com/patriciafiona/patriciafiona.github.io/main/hosting/resouces/furnitures/${item[3]}",
                      fit: BoxFit.fill,
                      width: screenWidth * .3,
                      loadingBuilder: (BuildContext context, Widget child,
                          ImageChunkEvent? loadingProgress) {
                        if (loadingProgress == null) return child;
                        return Center(
                          child: CircularProgressIndicator(
                            value: loadingProgress.expectedTotalBytes != null
                                ? loadingProgress.cumulativeBytesLoaded /
                                loadingProgress.expectedTotalBytes!
                                : null,
                          ),
                        );
                      },
                    ),
                  ),
                  Align(
                    alignment: Alignment.centerLeft,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            Text(
                              "${item[0]}%",
                              style: TextStyle(
                                color: yellowStabilo,
                                fontFamily: "Lufga",
                                fontSize: 20,
                                fontWeight: FontWeight.bold
                              ),
                            ),
                            const Text(
                              " Off",
                              style: TextStyle(
                                  color: Colors.white,
                                  fontFamily: "Lufga",
                                  fontSize: 20
                              ),
                            ),
                          ],
                        ),
                        SizedBox(
                          width: screenWidth * 0.3,
                          child: Text(
                            item[1],
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                            style: const TextStyle(
                                color: Colors.white,
                                fontFamily: "Lufga",
                                fontSize: 14
                            ),
                          ),
                        ),
                        const SizedBox(height: 8),
                        Row(
                          children: [
                            const Text(
                              "\$ ",
                              style: TextStyle(
                                  color: Colors.white,
                                  fontFamily: "Lufga",
                                  fontSize: 16,
                              ),
                            ),
                            Text(
                              item[2],
                              style: const TextStyle(
                                color: Colors.white,
                                fontFamily: "Lufga",
                                fontSize: 24,
                                fontWeight: FontWeight.bold
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                  Align(
                    alignment: Alignment.bottomRight,
                    child: ElevatedButton(
                      onPressed: () {},
                      style: ElevatedButton.styleFrom(
                        shape: const CircleBorder(),
                        padding: const EdgeInsets.all(4),
                        backgroundColor: yellowStabilo, // <-- Button color
                        foregroundColor: Colors.black, // <-- Splash color
                      ),
                      child: Transform.rotate(
                        angle: -45,
                        child: const IconButton(
                          icon: Icon(
                            Icons.arrow_forward,
                            size: 24.0,
                            color: Colors.black,
                          ),
                          onPressed: null,
                        ),
                      ),
                    ),
                  )
                ],
              )),
        ))
        .toList();

    return SingleChildScrollView(
      child: Container(
        width: screenWidth,
        color: Colors.transparent,
        padding: const EdgeInsets.only(top: 38, left: 16, right: 16),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [

            topSection(),

            const SizedBox(height: 16),

            bannerSection(Datadummy().homeBannerImgList, imageSliders),

            const SizedBox(height: 16),

            ChipList(
              listOfChipNames: Datadummy().chipOptions,
              activeBgColorList: const [Colors.white70],
              inactiveBgColorList: [transparentDarkBlue],
              activeTextColorList: const [Colors.black],
              inactiveTextColorList: const [Colors.white],
              activeBorderColorList: const [Colors.transparent],
              inactiveBorderColorList: const [Colors.transparent],
              listOfChipIndicesCurrentlySelected: [chipTag],
              borderRadiiList: [50],
              showCheckmark: false,
              extraOnToggle: (val) {
                setState(() {
                  chipTag = val;

                  //Update the data in gridview
                  furnitureList = [];
                  if(chipTag == 0){
                    //show all
                    getFurnituresfromApi();
                  }else{
                    //show by selected category name
                    selectedCategoryChip = Datadummy().chipOptions[chipTag].toLowerCase();
                    getFurnituresfromApiWithFilteredCategory(selectedCategoryChip);
                  }
                });
              },
            ),

            const SizedBox(height: 16),

            const Text(
              "Featured Product",
              style: TextStyle(
                  color: Colors.white,
                  fontSize: 16,
                  fontFamily: "Lufga"
              ),
            ),

            const SizedBox(height: 16),

            productsList(context, furnitureList),

            const SizedBox(height: 50),
          ],
        ),
      ),
    );
  }

  Widget productsList(BuildContext context, List<FurniturItemResponse> furnitures) {
    var size = MediaQuery.of(context).size;

    final double itemHeight = (size.height - kToolbarHeight - 24) / 3;
    final double itemWidth = size.width / 2;

    return SizedBox(
      height: 500,
        child: () {
          if (furnitures.isEmpty) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
          return RefreshIndicator(
            onRefresh: _pullRefresh,
            child: GridView.builder(
              controller: ScrollController(keepScrollOffset: false),
              shrinkWrap: true,
              scrollDirection: Axis.vertical,
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2, // number of items in each row
                mainAxisSpacing: 4.0, // spacing between rows
                crossAxisSpacing: 2.0, // spacing between columns,
                childAspectRatio: (itemWidth / itemHeight),
              ),
              itemCount: furnitures.length, // total number of items
              itemBuilder: (context, index) {
                return furnitureGridItem(furnitures[index], itemWidth);
              },
            ),
          );
        }
      ());
  }

  Card furnitureGridItem(FurniturItemResponse furniture, double itemWidth) {
      return Card(
        color: transparentDarkBlue,
        elevation: 4,
        child: InkWell(
          splashColor: Colors.blue.withAlpha(30),
          onTap: () {
            Navigator.push(
                context,
                PageTransition(
                  duration: const Duration(milliseconds: 500),
                    type: PageTransitionType.fade,
                    child: ProductDetailScreen(
                      productId: furniture.id!,
                    )
                )
            );
          },
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Stack(
              children: [
                Align(
                  alignment: Alignment.center,
                  child: Column(
                    children: [
                      Image.network(
                        "https://raw.githubusercontent.com/patriciafiona/patriciafiona.github.io/main/hosting/resouces/furnitures/${furniture.photos?[0]}",
                        fit: BoxFit.fill,
                        height: 120,
                        width: 120,
                        loadingBuilder: (BuildContext context, Widget child,
                            ImageChunkEvent? loadingProgress) {
                          if (loadingProgress == null) return child;
                          return Center(
                            child: CircularProgressIndicator(
                              value: loadingProgress.expectedTotalBytes != null
                                  ? loadingProgress.cumulativeBytesLoaded /
                                  loadingProgress.expectedTotalBytes!
                                  : null,
                            ),
                          );
                        },
                      ),
                      const Spacer(),
                      Row(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          SizedBox(
                            width: itemWidth / 2.5,
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  furniture.name ?? "Unknown name",
                                  style: const TextStyle(
                                      fontSize: 12,
                                      color: Colors.white,
                                      fontFamily: "Lufga"
                                  ),
                                  overflow: TextOverflow.ellipsis,
                                  maxLines: 1,
                                ),
                                Text(
                                  "\$${furniture.price}",
                                  style: const TextStyle(
                                      fontSize: 16,
                                      color: Colors.white,
                                      fontFamily: "Lufga",
                                      fontWeight: FontWeight.bold
                                  ),
                                ),
                              ],
                            ),
                          ),
                          const Spacer(),
                          ElevatedButton(
                            onPressed: () {},
                            style: ElevatedButton.styleFrom(
                              shape: const CircleBorder(),
                              padding: const EdgeInsets.all(8),
                              backgroundColor: transparentGray, // <-- Button color
                              foregroundColor: Colors.black, // <-- Splash color
                            ),
                            child: Transform.rotate(
                              angle: -45,
                              child: const Icon(
                                Icons.arrow_forward,
                                size: 18.0,
                                color: Colors.white,
                              ),
                            ),
                          )
                        ],
                      )
                    ],
                  )
                ),
                Align(
                  alignment: Alignment.topLeft,
                  child: ElevatedButton(
                    onPressed: () {},
                    style: ElevatedButton.styleFrom(
                      shape: const CircleBorder(),
                      padding: const EdgeInsets.all(4),
                      backgroundColor: transparentGray, // <-- Button color
                      foregroundColor: Colors.black, // <-- Splash color
                    ),
                    child: const Icon(
                      Icons.favorite_border_outlined,
                      size: 22.0,
                      color: Colors.white,
                    )
                  ),
                )
              ],
            )
          ),
        )
      );
  }

  Container bannerSection(List<List<String>> imgList, List<Widget> imageSliders){
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 0),
      decoration: BoxDecoration(
          color: transparentDarkBlue,
          borderRadius: const BorderRadius.all(Radius.circular(30))
      ),
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.only(left: 16.0, top: 8),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: imgList.asMap().entries.map((entry) {
                return GestureDetector(
                  onTap: () => _controller.animateToPage(entry.key),
                  child: Container(
                    width: _current == entry.key ? 22.0 : 12.0,
                    height: 3.0,
                    margin: const EdgeInsets.symmetric(vertical: 8.0, horizontal: 2.0),
                    decoration: BoxDecoration(
                        shape: BoxShape.rectangle,
                        color: (Theme.of(context).brightness == Brightness.dark
                            ? Colors.white
                            : Colors.white)
                            .withOpacity(_current == entry.key ? 1.0 : 0.4)),
                  ),
                );
              }).toList(),
            ),
          ),
          CarouselSlider(
            items: imageSliders,
            controller: _controller,
            options: CarouselOptions(
                autoPlay: true,
                enlargeCenterPage: true,
                onPageChanged: (index, reason) {
                  setState(() {
                    _current = index;
                  });
                }),
          ),
        ],
      ),
    );
  }

  Row topSection() {
    return Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "Elevate Your",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 18,
                      fontFamily: "Lufga"
                    ),
                  ),
                  Text(
                    "Living Space",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 22,
                        fontFamily: "Lufga",
                      fontWeight: FontWeight.w600
                    ),
                  ),
                ],
              ),
              const Spacer(),
              Container(
                padding: const EdgeInsets.all(8),
                decoration: BoxDecoration(
                  color: transparentGray,
                    borderRadius: const BorderRadius.all(Radius.circular(50))
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
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
                    ElevatedButton(
                      onPressed: () {},
                      style: ElevatedButton.styleFrom(
                        shape: const CircleBorder(),
                        padding: const EdgeInsets.all(15),
                        backgroundColor: Colors.white.withOpacity(.8), // <-- Button color
                        foregroundColor: Colors.black, // <-- Splash color
                      ),
                      child: const Icon(
                        Icons.shopping_bag_outlined,
                        size: 24.0,
                      ),
                    ),
                  ],
                ),
              )
            ],
          );
  }
}
