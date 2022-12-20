
import 'Collection.dart';

class Creator {
  late String id;
  late String name;
  late bool isVerified;
  late String joinDate;
  late String description;
  late List<Collection> collections;

  Creator({
    required this.id,
    required this.name,
    required this.isVerified,
    required this.joinDate,
    required this.description,
    required this.collections
  });
}