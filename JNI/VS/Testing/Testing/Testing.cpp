// Testing.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include "Library.h"
#include <iostream>
#include <vector>
#include <ctime>

using namespace std;

JNIEXPORT void JNICALL Java_Library_hello(JNIEnv * env, jobject obj) {
	cout << "Hello from C++" << endl;
};

JNIEXPORT jintArray JNICALL Java_Library_someRandoms(JNIEnv * env, jobject obj, jint asize) {
	// randomize seed
	srand((int)time(NULL));
	// skip first number
	rand();
	
	// create java array object with size
	jintArray result = env->NewIntArray(asize);
	if (result == NULL) return NULL;

	// create internal vector
	vector<jint> temp;

	// push random numbers to vector
	for (jint i = 0; i < asize; i++) {
		temp.push_back(rand() % 10);
	}

	// fill array object with vector contents by passing
	// pointer to its first element ( to its array )
	env->SetIntArrayRegion(result, 0, asize, &temp[0]);

	return result;
}