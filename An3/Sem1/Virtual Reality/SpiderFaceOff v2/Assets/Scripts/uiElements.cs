using UnityEngine;
using System.Collections;

public class uiElements : MonoBehaviour {
	public Texture skyTexture;
	public Texture normalTexture;

	private static uiElements _instance;
	public static uiElements Instance
	{
		get
		{
			if(_instance == null)
				_instance = (uiElements) (Resources.FindObjectsOfTypeAll(typeof(uiElements))[0]);
			//Debug.Log (_instance);
			return _instance;
		}
	}

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
