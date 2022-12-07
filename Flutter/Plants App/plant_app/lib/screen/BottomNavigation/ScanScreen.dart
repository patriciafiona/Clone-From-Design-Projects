import 'package:flutter/material.dart';

class ScanScreen extends StatefulWidget {
  const ScanScreen({Key? key}) : super(key: key);

  @override
  State<ScanScreen> createState() => _ScanScreenState();
}

class _ScanScreenState extends State<ScanScreen> {
  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
        body: SingleChildScrollView(
          child: Container(
            padding: const EdgeInsets.only(top: 60, left: 20, right: 20),
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage("assets/images/monstera_bg.jpeg"),
                fit: BoxFit.fill,
              ),
            ),
            child: Column(
              children: [
                Container(
                  margin: const EdgeInsets.only(bottom: 70),
                  width: double.infinity,
                  height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.7,
                  child: Image.asset(
                    "assets/images/scan_icon.png"
                  ),
                ),
                ElevatedButton(
                  onPressed: () {},
                  child: const Text(
                    "Scan Plant Now",
                    style: TextStyle(
                        color: Colors.white
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    shape: const StadiumBorder(),
                    padding: const EdgeInsets.all(15),
                    primary: const Color.fromRGBO(105, 171, 154, 1.0), // <-- Button color
                    onPrimary: const Color.fromRGBO(105, 171, 154, 0.7), // <-- Splash color
                  ),
                ),
                const SizedBox(height: 250)
              ],
            ),
          ),
        )
    );
  }
}
