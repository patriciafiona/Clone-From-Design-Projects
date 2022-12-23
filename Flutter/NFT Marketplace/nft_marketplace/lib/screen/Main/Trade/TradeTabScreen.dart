import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';

class TradeTabScreen extends StatefulWidget {
  const TradeTabScreen({Key? key}) : super(key: key);

  @override
  State<TradeTabScreen> createState() => _TradeTabScreenState();
}

class _TradeTabScreenState extends State<TradeTabScreen> {
  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Center(
      child: Container(
        padding: const EdgeInsets.all(20),
        width: double.infinity,
        height: (mediaQuery.size.height - mediaQuery.padding.vertical),
        child: Column(
          children: [
            const Spacer(),
            Lottie.asset(
                'assets/lotties/not_available.json',
                fit: BoxFit.cover,
            ),
            const SizedBox(height: 20),
            const Text(
              "Service not Available",
              style: TextStyle(
                  fontSize: 24,
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontFamily: 'FredokaOne'
              ),
            ),
            const Spacer(),
          ],
        ),
      ),
    );
  }
}
