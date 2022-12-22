import 'package:flutter/material.dart';
import '../../model/DataSource.dart';
import '../../model/entities/NFT.dart';

class DetailScreen extends StatefulWidget {
  static const routeName = '/detail_screen';
  final NFT data;

  const DetailScreen({
    super.key,
    required this.data
  });

  @override
  State<DetailScreen> createState() => _DetailScreenState();

}


class _DetailScreenState extends State<DetailScreen> with TickerProviderStateMixin {
  late AnimationController controller;

  var duration = 4329 ; //in second

  @override
  void initState() {
    controller = AnimationController(
      vsync: this,
      duration: Duration(seconds: duration),
    )..addListener(() {
      setState(() {});
    });
    controller.animateTo(duration.toDouble());
    super.initState();
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    var creator = DataSource().creators.where(
            (c) => c.id == widget.data.creatorId
    ).toList();
    var collection = creator[0].collections.where(
            (col) => col.id == widget.data.collectionId
    ).toList()[0];

    Widget checkVerified(){
      if(creator[0].isVerified){
        return const Icon(
          Icons.verified,
          color: Colors.blue,
          size: 10,
        );
      }else{
        return const SizedBox();
      }
    }

    return Scaffold(
      appBar: AppBar(
        title: SizedBox(
          width: double.infinity,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text(
                collection.name,
                style: const TextStyle(
                    color: Colors.white,
                    fontFamily: "FredokaOne",
                    fontSize: 18,
                   fontWeight: FontWeight.bold
                ),
              ),
              const Text(
                  "1h 12m 09s",
                 style: TextStyle(
                   color: Colors.grey,
                   fontFamily: "FredokaOne",
                   fontSize: 14
                 ),
              ),
            ],
          ),
        ),
        actions: [
          IconButton(
              onPressed: (){},
              icon: const Icon(Icons.group_work_outlined)
          )
        ],
        leading: IconButton(
            onPressed: () {},
            icon: const Icon(Icons.arrow_back)
        ),
      ),
      body: Container(
        width: double.infinity,
        height: double.infinity,
        color: Colors.black,
        child: SingleChildScrollView(
          child: Column(
            children: [
            SizedBox(
            width: double.infinity,
            height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.65,
            child: Stack(
                children: [
                  Container(
                      padding: const EdgeInsets.all(10),
                      width: double.infinity,
                      height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.65,
                      decoration: BoxDecoration(
                        image: DecorationImage(
                            image: AssetImage("assets/images/${widget.data.imagePath}"),
                            fit: BoxFit.cover),
                      )
                  ),
                  Positioned.fill(
                    child: Align(
                      alignment: Alignment.bottomCenter,
                      child: Container(
                        height: 90,
                        padding: const EdgeInsets.all(10.0),
                        color: const Color.fromRGBO(40, 40, 40, .9),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.start,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            CircleAvatar(
                              backgroundColor: const Color.fromRGBO(30, 30, 30, .9),
                              child: Container(
                                padding: const EdgeInsets.all(10),
                                child: Image.asset(
                                  "assets/images/ethereum-eth-logo.png"
                                ),
                              ),
                            ),
                            Expanded(
                                child: Container(
                                  padding: const EdgeInsets.only(left: 10),
                                  child: Column(
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      Text(
                                        "${widget.data.price} ETH",
                                        style: const TextStyle(
                                            fontSize: 18,
                                            color: Colors.white,
                                            fontWeight: FontWeight.bold,
                                            fontFamily: 'FredokaOne'
                                        ),
                                      ),
                                      Row(
                                        children: [
                                          Text(
                                            creator[0].name,
                                            style: const TextStyle(
                                                fontSize: 10,
                                                color: Colors.grey,
                                                fontWeight: FontWeight.bold,
                                                fontFamily: 'FredokaOne'
                                            ),
                                          ),
                                          const SizedBox(width: 5),
                                          checkVerified()
                                        ],
                                      ),
                                    ],
                                  ),
                                )
                            ),
                            Container(
                              margin: const EdgeInsets.symmetric(horizontal: 20),
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.center,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  const Icon(
                                      Icons.favorite_border_outlined,
                                     color: Colors.white,
                                    size: 20,
                                  ),
                                  const SizedBox(height: 5),
                                  Text(
                                    widget.data.totalFavorites.toString(),
                                    style: const TextStyle(
                                        color: Colors.white,
                                        fontSize: 14,
                                        fontWeight: FontWeight.bold,
                                        fontFamily: "FredokaOne"
                                    ),
                                  )
                                ],
                              ),
                            )
                          ],
                        ),
                      ),
                    ),
                  ),
                ]
              ),
            ),
              LinearProgressIndicator(
                value: controller.value,
                color: const Color.fromRGBO(83, 242, 109, 1),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
