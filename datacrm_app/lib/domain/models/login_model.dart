class LoginResponse {
  final Result? result;
  final bool success;

  LoginResponse({required this.result, required this.success});

  factory LoginResponse.fromJson(Map<String, dynamic> json) {
    return LoginResponse(
      result: json['result'] != null ? Result.fromJson(json['result']) : null,
      success: json['success'],
    );
  }
}

class Result {
  final String sessionName;
  final String userId;
  final String version;
  final String vtigerVersion;

  Result({
    required this.sessionName,
    required this.userId,
    required this.version,
    required this.vtigerVersion,
  });

  factory Result.fromJson(Map<String, dynamic> json) {
    return Result(
      sessionName: json['sessionName'],
      userId: json['userId'],
      version: json['version'],
      vtigerVersion: json['vtigerVersion'],
    );
  }
}
