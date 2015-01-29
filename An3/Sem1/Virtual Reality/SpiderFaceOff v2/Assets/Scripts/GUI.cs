using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GUI : MonoBehaviour {
	public GameObject plane;
	public MeshRenderer planeMesh;
	public GameObject mainButton;
	public GameObject gameButton;
	public Text gameText;
	public GameObject centerText;
	public GameObject endText;

	private static GUI _instance;
	public static GUI Instance
	{
		get
		{
			if(_instance == null)
				_instance = (GUI) (Resources.FindObjectsOfTypeAll(typeof(GUI))[0]);
			//Debug.Log (_instance);
			return _instance;
		}
	}
	// Use this for initialization
	void Start () {
		SpecialEffectsScript.PlaySleepingSound ();
		gameButton.SetActive (false);
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyUp(KeyCode.Escape)) {
			BackKeyPressed();
		}	
	}

	public void mainButtonClick () {
		planeMesh.renderer.material.mainTexture = uiElements.Instance.normalTexture;
		mainButton.SetActive (false);
		gameButton.SetActive (true);
		centerText.SetActive (false);
		gameButtonClick ();
	}

	public void gameButtonClick() {
		Debug.Log ("gameButtonClicked: "+GameScript.gameState);
		switch (GameScript.gameState) {
			case GameScript.GameStates.sleeping:				
				GameScript.spiderWakeUp();
				gameText.text = "Scared?\nTap to block the spider";
				endText.SetActive(false);
				GameScript.Instance.startLevel(1);
			break;

		case GameScript.GameStates.stopped:
				GameScript.spiderStartSleeping();
				gameText.text = "Touch to wake up the spider";
			break;

		case GameScript.GameStates.walking:
				if (GameScript.currentLevel<5) {
					GameScript.createWall();
					gameText.text = "";
				}
				else {					
					gameText.text = "Touch to restart";
					endText.SetActive(true);
					GameScript.spiderStartSleeping();
					GameScript.Instance.startLevel(6);					
				}
			break;
		case GameScript.GameStates.dead:
				gameText.text = "Touch to wake up the spider";
				GameScript.positionCameraAtStart();
				GameScript.Instance.boot.SetActive(false);
				GameScript.spiderStartSleeping();
				SpecialEffectsScript.PlaySleepingSound ();
			break;
		}



	}
	public void BackKeyPressed() {
		Application.Quit ();
	}


	
}
