import 'package:flutter/material.dart';

class FindTabScreen extends StatefulWidget {
  const FindTabScreen({Key? key}) : super(key: key);

  @override
  State<FindTabScreen> createState() => _FindTabScreenState();
}

class _FindTabScreenState extends State<FindTabScreen> with TickerProviderStateMixin {
  final double _myWallet = 6.284;
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 5, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          children: [
            SizedBox(
              width: 25,
              height: 25,
              child: Image.asset(
                  "assets/images/ethereum-eth-logo.png"
              ),
            ),
            Expanded(
              child: Text(
                "$_myWallet ETH",
                style: const TextStyle(
                  fontFamily: 'FredokaOne',
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                  fontSize: 14,
                ),
              ),
            ),
            IconButton(
                onPressed: () { },
                icon: const Icon(
                  Icons.notifications_outlined,
                  size: 25,
                  color: Colors.white,
                )
            ),
            IconButton(
              onPressed: () { },
              icon: const Icon(
                Icons.widgets_outlined,
                size: 25,
                color: Colors.white,
              ),
            )
          ],
        ),
        const SizedBox(height: 20),
        const Text(
          "Discover Unusual",
          style: TextStyle(
            fontFamily: 'FredokaOne',
            fontWeight: FontWeight.bold,
            color: Colors.white,
            fontSize: 24,
          ),
        ),
        const Text(
          "NFTs",
          style: TextStyle(
            fontFamily: 'FredokaOne',
            fontWeight: FontWeight.bold,
            color: Colors.white,
            fontSize: 48,
          ),
        ),
        const SizedBox(height: 20),
        TabBar(
          isScrollable: true, // Required
          unselectedLabelColor: Colors.white30, // Other tabs color
          labelPadding: const EdgeInsets.symmetric(horizontal: 10), // Space between tabs
          indicator: const UnderlineTabIndicator(
            borderSide: BorderSide(
                color: Color.fromRGBO(83, 242, 109, 1),
                width: 3
            ), // Indicator height
            insets: EdgeInsets.symmetric(horizontal: 18), // Indicator width
          ),
          controller: _tabController,
          tabs: const [
            Tab(
                text: 'All NFTs'
            ),
            Tab(
                text: 'Trending'
            ),
            Tab(
                text: 'Art'
            ),
            Tab(
                text: 'Collectibles'
            ),
            Tab(
                text: 'Sports'
            ),
          ],
        ),
        Container(
          width: double.infinity,
          height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.65,
          child: TabBarView(
            controller: _tabController,
            children: const <Widget>[
              Center(
                child: Text(
                  "It's cloudy here",
                  style: TextStyle(color: Colors.white)
                ),
              ),
              Center(
                child: Text(
                    "It's rainy here",
                    style: TextStyle(color: Colors.white)
                ),
              ),
              Center(
                child: Text(
                    "It's sunny here",
                    style: TextStyle(color: Colors.white)
                ),
              ),
              Center(
                child: Text(
                    "It's rainy here",
                    style: TextStyle(color: Colors.white)
                ),
              ),
              Center(
                child: Text(
                    "It's sunny here",
                    style: TextStyle(color: Colors.white)
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
