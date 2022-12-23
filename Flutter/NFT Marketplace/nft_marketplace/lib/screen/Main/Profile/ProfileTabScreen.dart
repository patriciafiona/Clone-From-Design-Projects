import 'package:flutter/material.dart';
import 'package:nft_marketplace/model/DataSource.dart';

import '../../../widget/NFTSmallItem.dart';

class ProfileTabScreen extends StatefulWidget {
  const ProfileTabScreen({Key? key}) : super(key: key);

  @override
  State<ProfileTabScreen> createState() => _ProfileTabScreenState();
}

class _ProfileTabScreenState extends State<ProfileTabScreen> {
  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    var dataSource = DataSource();
    final orientation = MediaQuery.of(context).orientation;

    const List<String> myCollectionName = [
      "YuGiYn #698", "YuGiYn #4477",
      "YuGiYn #6678", "YuGiYn #8311",
      "YuGiYn #5533"
    ];

    var myCollectionData = dataSource.allNft.where(
            (n) => myCollectionName.contains(n.name)
    ).toList();

    return SingleChildScrollView(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SizedBox(
            width: double.infinity,
            height: 100,
            child: Image.asset(
              "assets/images/NFT_Marketplace_profile_banner.png",
              fit: BoxFit.cover,
            ),
          ),
          Container(
            width: double.infinity,
            transform: Matrix4.translationValues(0.0, -50.0, 0.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Container(
                  width: 100.0,
                  height: 100.0,
                  decoration: BoxDecoration(
                    color: Colors.black,
                    image: const DecorationImage(
                      image: AssetImage("assets/images/YuGiYn_#698.png"),
                      fit: BoxFit.cover,
                    ),
                    borderRadius: const BorderRadius.all( Radius.circular(50.0)),
                    border: Border.all(
                      color: Colors.grey,
                      width: 4.0,
                    ),
                  ),
                ),
                const SizedBox(height: 20),
                const Text(
                  "Patricia Fiona",
                  style: TextStyle(
                      fontSize: 24,
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                      fontFamily: 'FredokaOne'
                  ),
                ),
                const SizedBox(height: 20),
                const Text(
                  "Like NFT\'s like love my self."
                      "\nCollecting next-gen Avatars by YuGi-Labs",
                  style: TextStyle(
                      fontSize: 14,
                      color: Colors.grey,
                      fontFamily: 'FredokaOne',
                  ),
                  textAlign: TextAlign.center,
                )
              ],
            ),
          ),
          Container(
            padding: const EdgeInsets.only(left: 20, right: 20, bottom: 10),
            child: const Text(
              "My Collection",
              style: TextStyle(
                  fontSize: 24,
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontFamily: 'FredokaOne'
              ),
            ),
          ),
          Container(
            height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.5,
            margin: const EdgeInsets.only(bottom: 150),
            child: GridView.builder(
              padding: EdgeInsets.zero,
              itemCount: myCollectionData.length,
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: (orientation == Orientation.portrait) ? 2 : 3),
              itemBuilder: (BuildContext context, int index) {
                return NFTSmallItem(
                  data: myCollectionData[index],
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
