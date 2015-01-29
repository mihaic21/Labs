using UnityEngine;
using System.Collections;

public class uiActions : MonoBehaviour {

	// Use this for initialization

	private static uiActions _instance;
	public static uiActions Instance
	{
		get
		{
			if(_instance == null)
				_instance = (uiActions) (Resources.FindObjectsOfTypeAll(typeof(uiActions))[0]);
			//Debug.Log (_instance);
			return _instance;
		}
	}


	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void mainButtonClick() {
		GUI.Instance.mainButtonClick();
	}

	public void gameButtonClick() {
		GUI.Instance.gameButtonClick ();
	}
}
