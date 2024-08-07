import 'dart:convert';

import 'package:carousel_slider_plus/carousel_controller.dart';
import 'package:carousel_slider_plus/carousel_slider_plus.dart';
import 'package:flutter/material.dart';
import 'package:furniture_app/utils/extension/HexColor.dart';
import 'package:http_status/http_status.dart';
import 'package:page_transition/page_transition.dart';
import 'package:slidable_button/slidable_button.dart';

import '../../model/api/RestClient.dart';
import '../../model/entity/FurnituesResponse.dart';
import '../../model/entity/FurniturItemResponse.dart';
import '../../utils/Constants.dart';

class ProductDetailScreen extends StatefulWidget {
  const ProductDetailScreen({super.key, required this.productId});

  final int productId;

  @override
  State<ProductDetailScreen> createState() => _ProductDetailScreenState();
}

class _ProductDetailScreenState extends State<ProductDetailScreen> {
  //Furniture data from API
  FurniturItemResponse? furnitureData;

  //Carousel
  int _current = 0;
  final CarouselControllerPlus _controller = CarouselControllerPlus();

  List<String> photoList = [];

  void getFurnituresfromApi() async {
    RestClient.getFurnitures().then((response) {
      setState(() {
        if (response.statusCode == HttpStatusCode.ok) { // res.statusCode == 200
          Map<String, dynamic> mapOfFurnitures = json.decode(response.body);
          var furnituresResponse = FurnituresResponse.fromJson(mapOfFurnitures);
          var furnitureList = furnituresResponse.results!;
          logger.i("SUCCESS GET FURNITURE DATA: $furnitureList");

          //Filter data by product id
          furnitureData = furnitureList.where((furniture) => furniture.id == widget.productId).toList()[0];
          photoList = furnitureList.where((furniture) => furniture.id == widget.productId).toList()[0].photos!;
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

    final List<Widget> imageSliders = photoList
        .map((item) => Container(
          margin: const EdgeInsets.all(3.0),
          child: ClipRRect(
              borderRadius: const BorderRadius.all(Radius.circular(5.0)),
              child: Stack(
                children: <Widget>[
                  Image.network(
                    "https://raw.githubusercontent.com/patriciafiona/patriciafiona.github.io/main/hosting/resouces/furnitures/$item",
                    fit: BoxFit.fitHeight,
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
                ],
              )),
        ))
        .toList();

    return Scaffold(
      body: Container(
        width: screenWidth,
        height: screenHeight,
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage("assets/image/background.png"),
            fit: BoxFit.cover,
          ),
        ),
        padding: const EdgeInsets.only(left: 16, right: 16, bottom: 32, top: 48),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
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
                    child: Text(
                      "Product Detail",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 16,
                        fontFamily: "Lufga",
                      ),
                      textAlign: TextAlign.center,
                    )
                ),
                ElevatedButton(
                  onPressed: () {},
                  style: ElevatedButton.styleFrom(
                    shape: const CircleBorder(),
                    padding: const EdgeInsets.all(8),
                    backgroundColor: transparentGray, // <-- Button color
                    foregroundColor: Colors.black, // <-- Splash color
                  ),
                  child: const Icon(
                    Icons.favorite,
                    size: 20.0,
                    color: Colors.white,
                  ),
                ),
              ],
            ),
            Builder(
              builder: (context) {
                if (furnitureData == null){
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                }else{
                  return Expanded(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.start,
                        crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Center(
                              child: SizedBox(
                                height: 300,
                                width: screenWidth,
                                child: Column(
                                    children: [
                                      Expanded(
                                        child: CarouselSlider(
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
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.symmetric(horizontal: 16.0),
                                        child: Row(
                                          mainAxisAlignment: MainAxisAlignment.start,
                                          children: photoList.asMap().entries.map((entry) {
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
                                                        : Colors.grey)
                                                        .withOpacity(_current == entry.key ? 1.0 : 0.4)),
                                              ),
                                            );
                                          }).toList(),
                                        ),
                                      ),
                                    ]
                                ),
                              ),
                            ),

                            const SizedBox(height: 16),

                            Card(
                              color: transparentDarkBlue,
                              elevation: 4,
                              child: Container(
                                width: screenWidth,
                                padding: const EdgeInsets.all(16.0),
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.start,
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text(
                                      furnitureData!.name ?? "Unknown name",
                                      style: const TextStyle(
                                          fontSize: 16,
                                          color: Colors.white,
                                          fontFamily: "Lufga",
                                        fontWeight: FontWeight.bold
                                      ),
                                    ),

                                    const SizedBox(height: 8),

                                    SizedBox(
                                      width: screenWidth,
                                      height: 100,
                                      child: SingleChildScrollView(
                                        child: Text(
                                          loremIpsum,
                                          style: const TextStyle(
                                            fontSize: 12,
                                            color: Colors.white,
                                            fontFamily: "Lufga",
                                          ),
                                        ),
                                      ),
                                    ),

                                    const SizedBox(height: 16),

                                    Column(
                                      mainAxisAlignment: MainAxisAlignment.start,
                                      crossAxisAlignment: CrossAxisAlignment.start,
                                      children: [
                                        Row(
                                          children: [
                                            Container(
                                              width: 30,
                                              height: 30,
                                              decoration: BoxDecoration(
                                                shape: BoxShape.circle,
                                                color: HexColor.fromHex(furnitureData!.color!),
                                              ),
                                            ),
                                            Expanded(
                                              flex: 1,
                                              child: Container(
                                                width: 60,
                                                height: 60,
                                              ),
                                            )
                                          ],
                                        ),
                                        Row(
                                          children: [
                                            Expanded(
                                              flex: 1,
                                              child: Text(
                                                "\$${furnitureData!.price}",
                                                style: const TextStyle(
                                                    fontSize: 28,
                                                    color: Colors.white,
                                                    fontFamily: "Lufga",
                                                    fontWeight: FontWeight.bold
                                                ),
                                              ),
                                            ),
                                            Expanded(
                                              flex: 1,
                                              child: HorizontalSlidableButton(
                                                height: 40,
                                                buttonWidth: 40.0,
                                                color: yellowStabilo,
                                                buttonColor: darkBlue,
                                                dismissible: false,
                                                label: Center(
                                                    child: Image.asset(
                                                      "assets/gif/arrow_right.gif",
                                                      width: 30,
                                                    )
                                                ),
                                                child: const Padding(
                                                  padding: EdgeInsets.all(8.0),
                                                  child: Row(
                                                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                                    children: [
                                                      Spacer(),
                                                      Text(
                                                        'Add to cart',
                                                        textAlign: TextAlign.center,
                                                        style: TextStyle(
                                                            color: Colors.black,
                                                            fontSize: 12,
                                                            fontWeight: FontWeight.bold
                                                        ),
                                                      ),
                                                      SizedBox(width: 16),
                                                    ],
                                                  ),
                                                ),
                                                onChanged: (position) {
                                                  if (position == SlidableButtonPosition.end) {
                                                    setState(() {
                                                      // Navigator.push(
                                                      //     context,
                                                      //     PageTransition(
                                                      //         type: PageTransitionType.fade,
                                                      //         child: MainScreen()
                                                      //     )
                                                      // );
                                                    });
                                                  }
                                                },
                                              ),
                                            ),
                                          ],
                                        ),
                                      ],
                                    )
                                  ],
                                ),
                              ),
                            )
                          ]
                      )
                  );
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
