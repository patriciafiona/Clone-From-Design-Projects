import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:furniture_app/screens/HomeScreen.dart';
import 'package:slidable_button/slidable_button.dart';

class GetStartedScreen extends StatefulWidget {
  const GetStartedScreen({super.key});

  @override
  State<GetStartedScreen> createState() => _GetStartedScreenState();
}

class _GetStartedScreenState extends State<GetStartedScreen> {

  int _current = 0;
  final CarouselController _controller = CarouselController();

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double screenHeight = MediaQuery.of(context).size.height;

    final List<String> imgList = [
      "get_started_banner_01.png",
      "get_started_banner_02.png",
      "get_started_banner_03.png",
    ];

    final List<Widget> imageSliders = imgList
        .map((item) => Container(
      child: Container(
        margin: const EdgeInsets.all(3.0),
        child: ClipRRect(
            borderRadius: const BorderRadius.all(Radius.circular(5.0)),
            child: Stack(
              children: <Widget>[
                Image.asset(
                  "assets/image/$item",
                  fit: BoxFit.cover,
                  width: screenWidth * 0.9,
                  height: 1500,
                )
              ],
            )),
      ),
    ))
        .toList();

    return Scaffold(
      backgroundColor: Colors.black,
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Container(
            height: screenHeight * 0.87,
            width: screenWidth,
            padding: const EdgeInsets.only(top: 70.0),
            child: Column(
                children: [
                  Expanded(
                    child: CarouselSlider(
                      items: imageSliders,
                      carouselController: _controller,
                      options: CarouselOptions(
                          autoPlay: true,
                          enlargeCenterPage: true,
                          aspectRatio: 0.7,
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
          const Spacer(),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 16),
            child: Row(
              children: [
                RawMaterialButton(
                  onPressed: () {},
                  fillColor: Colors.white70.withOpacity(0.15),
                  padding: const EdgeInsets.all(24.0),
                  shape: const CircleBorder(),
                  child: const Center(
                    child: Icon(
                      Icons.arrow_back_ios,
                      size: 16.0,
                      color: Colors.white,
                    ),
                  ),
                ),
                Expanded(
                  child: HorizontalSlidableButton(
                    height: 60,
                    buttonWidth: 60.0,
                    color: Colors.white70.withOpacity(0.15),
                    buttonColor: const Color.fromRGBO(251, 255, 98, 1),
                    dismissible: false,
                    label: const Center(
                        child: Icon(
                          Icons.arrow_forward_ios,
                          color: Colors.black,
                          size: 16,
                        )
                    ),
                    child: const Padding(
                      padding: EdgeInsets.all(8.0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Spacer(),
                          Text(
                              'Start',
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 16,
                            ),
                          ),
                          Spacer(),
                        ],
                      ),
                    ),
                    onChanged: (position) {
                      if (position == SlidableButtonPosition.end) {
                        setState(() {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => const HomeScreen()),
                          );
                        });
                      }
                    },
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

