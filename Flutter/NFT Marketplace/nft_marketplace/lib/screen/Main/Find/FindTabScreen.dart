import 'package:flutter/material.dart';
import 'package:nft_marketplace/model/DataSource.dart';
import 'package:nft_marketplace/widget/NFTBigItem.dart';

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
    final dataSource = DataSource();

    const List<int> trendingDataPosition = [
      2, 8, 9, 14, 17, 18, 21, 22
    ];

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
        SizedBox(
          width: double.infinity,
          height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.75,
          child: TabBarView(
            controller: _tabController,
            children: <Widget>[
              ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: dataSource.allNft.length,
                itemBuilder: (_, index) {
                  return NFTBigItem(
                    data: dataSource.allNft[index],
                  );
                },
              ),
              ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: dataSource.allNft.length,
                itemBuilder: (_, index) {
                  if(trendingDataPosition.contains(index)) {
                    return NFTBigItem(
                      data: dataSource.allNft[index],
                    );
                  }else{
                    return const SizedBox();
                  }
                },
              ),
              ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: dataSource.allNft.length,
                itemBuilder: (_, index) {
                  if(dataSource.allNft[index].creatorId == "cr_00002_102021") {
                    return NFTBigItem(
                      data: dataSource.allNft[index],
                    );
                  }else{
                    return const SizedBox();
                  }
                },
              ),
              ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: dataSource.allNft.length,
                itemBuilder: (_, index) {
                  if(dataSource.allNft[index].creatorId == "cr_00001_102022" || dataSource.allNft[index].creatorId == "cr_00003_112021") {
                    return NFTBigItem(
                      data: dataSource.allNft[index],
                    );
                  }else{
                    return const SizedBox();
                  }
                },
              ),
              const Center(
                child: Text(
                    "It's sports here",
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
