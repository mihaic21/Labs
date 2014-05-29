#include <time.h>
bool isDigit(char c){ if(c < '0' || c > '9'){ return false; }else{ return true; }}

bool checkDualDigitNumber(char d, char u, int lowBound, int hiBound){
	if(isDigit(d) && isDigit(u)){
		int nr = (d - '0') * 10 + (u - '0');
		if(nr < lowBound || nr > hiBound){
			return false;		
		}
	}else { return false; }
}

bool checkQuadDigitNumber(char t, char h, char d, char u, int lowBound, int hiBound){
	if(isDigit(t) && isDigit(h) && isDigit(d) && isDigit(u)){
		int nr = (t - '0') * 1000 + (h - '0') * 100 + (d - '0') * 10 + (u - '0');
		if(nr < lowBound || nr > hiBound){
			return false;		
		}
	}else { return false; }
}

bool validateTime(char* m){
	if(m[0] != 'T' || m[1] != 'I' || m[2] != 'M' || m[3] != 'E' || m[4] != ' ' || m[7] != ':' || m[10] != ':'){
		return false;
	}

	return (checkDualDigitNumber(m[5], m[6], 0, 23) && checkDualDigitNumber(m[8], m[9], 0, 59) && checkDualDigitNumber(m[11], m[12], 0, 59));
}

bool validateDate(char* d){
	if(d[0] != 'D' || d[1] != 'A' || d[2] != 'T' || d[3] != 'E' || d[4] != ' ' || d[7] != ':' || d[10] != ':'){
		return false;
	}

	return (checkDualDigitNumber(d[5], d[6], 1, 31) && checkDualDigitNumber(d[8], d[9], 1, 12) && checkQuadDigitNumber(d[11], d[12], d[13], d[14], 0, 2015));
}

bool validateAnswer(char* msg){
	if(strlen(msg) == 13){
		return validateTime(msg);
	}else if(strlen(msg) == 15){
		return validateDate(msg);
	}else{
		return false;
	}
}
