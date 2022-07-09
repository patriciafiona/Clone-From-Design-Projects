import 'package:flutter/material.dart';
import 'package:plant_app/screen/BottomNavigation/BottomNavigationScreen.dart';
import 'package:plant_app/widget/IntroSlideWidget.dart';

class WelcomeScreen extends StatelessWidget {
  static const routeName = "/welcome_screen";
  const WelcomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.only(top: 60, bottom: 15),
          decoration: const BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/images/background.jpg"),
              fit: BoxFit.fill,
            ),
          ),
          child: Column(
            children: [
              IntrinsicHeight(
                child: Row(
                  children: [
                    const RotationTransition(
                      turns: AlwaysStoppedAnimation(-90 / 360),
                      child: Text(
                          "Planto.Shop"
                      ),
                    ),
                    const VerticalDivider(
                      width: 20,
                      thickness: 1,
                      indent: 20,
                      endIndent: 0,
                      color: Colors.grey,
                    ),
                    SizedBox(
                      width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.5,
                      child: const Text(
                        "Plant a tree for life",
                        style: TextStyle(
                            fontSize: 36,
                            fontWeight: FontWeight.bold
                        ),
                      ),
                    )
                  ],
                ),
              ),
              SizedBox(
                width: double.infinity,
                height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.75,
                child: const IntroSlideWidget()
              ),
              ElevatedButton(
                onPressed: () {
                  Navigator.pushNamed(context, BottomNavigationScreen.routeName);
                },
                child: const Text(
                  "GO",
                  style: TextStyle(
                    color: Colors.white
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  shape: const CircleBorder(),
                  padding: const EdgeInsets.all(20),
                  primary: const Color.fromRGBO(105, 171, 154, 1.0), // <-- Button color
                  onPrimary: const Color.fromRGBO(105, 171, 154, 0.7), // <-- Splash color
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
