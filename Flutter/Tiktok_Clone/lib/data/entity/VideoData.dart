import 'package:uuid/uuid.dart';

class VideoData {
  var uuid = const Uuid();
  late final String userId;
  late final String username;
  late final bool isVerified;
  late final String description;
  late final int totalLikes;
  late final int totalShare;
  late final int totalComment;
  late final int totalBookmark;
  late final List<String> tags;
  late final String videoSource;
  late final String musicSource;
  late final String musicImage;
  late final String profileSource;

  VideoData({
    required this.userId,
    required this.username,
    required this.isVerified,
    required this.description,
    required this.totalLikes,
    required this.totalShare,
    required this.totalComment,
    required this.totalBookmark,
    required this.tags,
    required this.videoSource,
    required this.musicSource,
    required this.musicImage,
    required this.profileSource
  });

}