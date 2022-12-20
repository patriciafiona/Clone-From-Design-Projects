
import 'package:uuid/uuid.dart';
import 'Creator.dart';

class NFT{
  var uuid = const Uuid();

  late String id = uuid.v4();
  late String creatorId;
  late String collectionId;
  late String name;
  late double price;
  late double lastSale;
  late int totalViews;
  late int totalFavorites;
  late String imagePath;

  NFT({
    required this.creatorId,
    required this.collectionId,
    required this.name,
    required this.price,
    required this.lastSale,
    required this.totalViews,
    required this.totalFavorites,
    required this.imagePath
  });
}