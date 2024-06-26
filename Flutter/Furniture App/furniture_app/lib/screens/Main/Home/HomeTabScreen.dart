import 'package:carousel_slider_plus/carousel_slider_plus.dart';
import 'package:flutter/material.dart';
import 'package:blur/blur.dart';

import '../../../utils/Constants.dart';

class HomeTabScreen extends StatefulWidget {
  const HomeTabScreen({super.key});

  @override
  State<HomeTabScreen> createState() => _HomeTabScreenState();
}

class _HomeTabScreenState extends State<HomeTabScreen> {
  int _current = 0;
  final CarouselControllerPlus _controller = CarouselControllerPlus();

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.sizeOf(context).width;
    double screenHeight = MediaQuery.sizeOf(context).height;

    final List<List<String>> imgList = [
      ["35", "Modway Engage Accent Chair with Ottoman", "1049.0", "321227_01.png"],
      ["44", "Rooms to Go Ridgewater Chair", "499.0", "531354_01.png"],
      ["72", "Mitchell Gold + Bob Williams English Roll Arm Club Chair", "2000.0", "527866_01.png"],
    ];

    final List<Widget> imageSliders = imgList
        .map((item) => Container(
      child: Container(
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
                    width: 150,
                    height: 150,
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
                )
              ],
            )),
      ),
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
            TopSection(),
            const SizedBox(height: 16),
            Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: transparentGray,
                borderRadius: const BorderRadius.all(Radius.circular(30))
              ),
              child: Column(
                children: [
                  Row(
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
            ),
            const SizedBox(height: 16),
          ],
        ),
      ),
    );
  }

  Row TopSection() {
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
                      fontSize: 20,
                      fontFamily: "Lufga"
                    ),
                  ),
                  Text(
                    "Living Space",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 28,
                        fontFamily: "Lufga",
                      fontWeight: FontWeight.w600
                    ),
                  ),
                ],
              ),
              const Spacer(),
              Container(
                decoration: BoxDecoration(
                  color: transparentGray,
                    borderRadius: const BorderRadius.all(Radius.circular(30))
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
                        padding: const EdgeInsets.all(20),
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
