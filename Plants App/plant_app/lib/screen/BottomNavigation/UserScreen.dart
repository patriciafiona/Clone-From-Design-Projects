import 'dart:math';

import 'package:flutter/material.dart';

class UserScreen extends StatefulWidget {
  const UserScreen({Key? key}) : super(key: key);

  @override
  State<UserScreen> createState() => _UserScreenState();
}

class _UserScreenState extends State<UserScreen> {
    List<String> listAvatar = [
    "assets/images/avatar_01.png",
    "assets/images/avatar_02.png",
    "assets/images/avatar_03.png"
  ];

  String getRandomAvatarIcon(){
    Random rnd = Random();
    int min = 0;
    int max = 3;
    var r = min + rnd.nextInt(max - min);
    return listAvatar[r];
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
        body: SingleChildScrollView(
          child: Container(
            width: double.infinity,
            padding: EdgeInsets.only(top: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.3, bottom: 15),
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage("assets/images/background.jpg"),
                fit: BoxFit.fill,
              ),
            ),
            child: Column(
              children: [
                SizedBox(
                  width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.5,
                  child: Image.asset(getRandomAvatarIcon()),
                ),
                const SizedBox(height: 50),
                const Text(
                  "Sign in",
                  style: TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold
                  ),
                ),
                Container(
                  margin: const EdgeInsets.all(10),
                  padding: EdgeInsets.only(bottom: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.4),
                  width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.65,
                  child: Column(
                    children: [
                      ElevatedButton(
                        onPressed: () {},
                        child: Row(
                          children: [
                            Container(
                              width: 20,
                              height: 20,
                              margin: const EdgeInsets.only(right: 10),
                              child: Image.asset("assets/images/google_icon.png"),
                            ),
                            const Expanded(
                              child: Text(
                                'Sign in with Google',
                                textAlign: TextAlign.center,
                              ),
                            )
                          ],
                        ),
                        style: ElevatedButton.styleFrom(
                          shape: const StadiumBorder(),
                          padding: const EdgeInsets.all(15),
                          primary: Colors.white, // <-- Button color
                          onPrimary: Colors.grey, // <-- Splash color
                        ),
                      ),
                      const SizedBox(height: 10),
                      ElevatedButton(
                        onPressed: () {},
                        child: Row(
                          children: [
                            Container(
                              width: 20,
                              height: 20,
                              margin: const EdgeInsets.only(right: 10),
                              child: Image.asset("assets/images/fb_icon.png"),
                            ),
                            const Expanded(
                              child: Text(
                                'Sign in with Facebook',
                                textAlign: TextAlign.center,
                              ),
                            )
                          ],
                        ),
                        style: ElevatedButton.styleFrom(
                          shape: const StadiumBorder(),
                          padding: const EdgeInsets.all(15),
                          primary: Colors.white, // <-- Button color
                          onPrimary: Colors.grey, // <-- Splash color
                        ),
                      ),
                    ],
                  ),
                )
              ],
            ),
          ),
        )
    );
  }
}
