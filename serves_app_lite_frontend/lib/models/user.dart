
import 'login.dart';

class User {
  String? id;
  String? username;
  String? avatar;

  User({this.id, this.username, this.avatar});

    User.fromLoginResponse(LoginResponse response) {
      this.id = response.id;
      this.username = response.username;
      this.avatar = response.avatar;
    }
}

class UserResponse extends User {

  UserResponse(id, username, fullName, avatar) : super(id: id, username: username, avatar: avatar);

  UserResponse.fromJson(Map<String, dynamic> json) {
  id = json['id'];
  username = json['username'];
  avatar = json['avatar'];
}
  Map<String, dynamic> toJson() {
  final Map<String, dynamic> data = new Map<String, dynamic>();
  data['id'] = this.id;
  data['username'] = this.username;
  data['avatar'] = this.avatar;
  return data;
}

}