import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:intro_slider/intro_slider.dart';

class IntroSlideWidget extends StatefulWidget {
  const IntroSlideWidget({Key? key}) : super(key: key);

  @override
  State<IntroSlideWidget> createState() => _IntroSlideWidgetState();
}

class _IntroSlideWidgetState extends State<IntroSlideWidget> {
  List<Slide> slides = [];
  late Function goToTab;

  @override
  void initState() {
    super.initState();

    slides.add(
      Slide(
        title: "Worldwide delivery",
        description:
        "Within 10-15 days",
        pathImage: "assets/images/plant_02.png",
      ),
    );
    slides.add(
      Slide(
        title: "Guaranteed plant freshness",
        description:
        "1 month freshness guarantee",
        pathImage: "assets/images/plant_10.png",
      ),
    );
    slides.add(
      Slide(
        title: "Worldwide delivery",
        description:
        "Within 10-15 days",
        pathImage: "assets/images/plant_04.png",
      ),
    );
  }

  void onDonePress() {
    // Back to the first tab
    goToTab(0);
  }

  void onTabChangeCompleted(index) {
    // Index of current tab is focused
    // log("onTabChangeCompleted, index: $index");
  }

  ButtonStyle myButtonStyle() {
    return ButtonStyle(
      shape: MaterialStateProperty.all<OutlinedBorder>(const StadiumBorder()),
      backgroundColor: MaterialStateProperty.all<Color>(const Color(0x33ffcc5c)),
      overlayColor: MaterialStateProperty.all<Color>(const Color(0x33ffcc5c)),
    );
  }

  List<Widget> renderListCustomTabs() {
    final mediaQuery = MediaQuery.of(context);
    return List.generate(
      slides.length,
          (index) => SizedBox(
        width: double.infinity,
        height: double.infinity,
        child: Container(
          margin: const EdgeInsets.only(bottom: 20.0, top: 20.0),
          child: ListView(
            children: <Widget>[
              GestureDetector(
                child: Image.asset(
                  slides[index].pathImage!,
                  width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.7,
                  height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.45,
                  fit: BoxFit.contain,
                ),
              ),
              Container(
                margin: const EdgeInsets.only(top: 10.0),
                child: Text(
                  slides[index].title!,
                  style: const TextStyle(
                    color: Colors.grey,
                    fontFamily: 'Aller',
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
              Container(
                margin: const EdgeInsets.only(top: 5.0),
                child: Text(
                  slides[index].description ?? '',
                  style: const TextStyle(
                    color: Colors.grey,
                    fontFamily: 'Aller',
                    fontSize: 16
                  ),
                  textAlign: TextAlign.center,
                  maxLines: 5,
                  overflow: TextOverflow.ellipsis,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return IntroSlider(
      // Dot indicator
      colorDot: const Color.fromRGBO(214, 234, 196, 1.0),
      sizeDot: 13.0,
      typeDotAnimation: DotSliderAnimation.SIZE_TRANSITION,

      // Tabs
      listCustomTabs: renderListCustomTabs(),
      refFuncGoToTab: (refFunc) {
        goToTab = refFunc;
      },

      // Behavior
      scrollPhysics: const BouncingScrollPhysics(),

      // Show or hide status bar
      hideStatusBar: true,

      // On tab change completed
      onTabChangeCompleted: onTabChangeCompleted,
    );
  }
}
