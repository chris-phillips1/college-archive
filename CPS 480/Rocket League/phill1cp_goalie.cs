using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class phill1cp_goalie : CarController {

	float vPos = 0;
	float hPos = 0;
	float hDirection = 1;
	float direction = 1;
	int offset = 10;
	int i = 0;

	// Use this for initialization
	void Start () {
		playerName = "phill1cp-goalie";
		Init();
		StartCoroutine(UpdateControls());
	}
	
	IEnumerator UpdateControls(){
		while (1==1){
			yield return new WaitForSeconds(0.1f);

			controller.horizontalSetting = hDirection;
			hDirection += direction * .1f;
			if (hDirection < -1f || hDirection > 1f){
				direction*=-1;
			}

		}
	}
}
