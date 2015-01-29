using UnityEngine;
using System.Collections;

public class SpiderScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnCollisionEnter(Collision col) {
		if (col.gameObject.tag == "Zid" && GameScript.gameState != GameScript.GameStates.wallHit) {
			SpecialEffectsScript.PlayAttackSound();
			WallScript.disableWall();
			GameScript.gameState = GameScript.GameStates.wallHit;
			GameScript.advanceLevel(0.1f);
			Debug.Log ("Lovit");
		}

	}
}
