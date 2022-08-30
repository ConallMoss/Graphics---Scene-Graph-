#version 140

in vec3 wc_frag_normal;        	// fragment normal in world coordinates (wc_)
in vec2 frag_texcoord;			// texture UV coordinates
in vec3 wc_frag_pos;			// fragment position in world coordinates

out vec3 color;			        // pixel colour

uniform sampler2D tex;  		  // 2D texture sampler
uniform samplerCube skybox;		  // Cubemap texture used for reflections
uniform vec3 wc_camera_position;  // Position of the camera in world coordinates

//Static Light stuff


vec3 C_spec = vec3(1,1,1);
vec3 I_a = vec3(0.1,0.1,0.1);
vec3 I_i = vec3(0.941,0.968,1);
float k_d = 0.4;
float k_s = 0.75;
int alpha = 32;

vec3 lightPos = vec3(-1, 3, -1);


// Tone mapping and display encoding combined
vec3 tonemap(vec3 linearRGB)
{
    float L_white = 0.8; // Controls the brightness of the image

    float inverseGamma = 1./2.2;
    return pow(linearRGB/L_white, vec3(inverseGamma)); // Display encoding - a gamma
}

/*
Vector3 refDir = ray.getDirection().scale(-1).normalised().reflectIn(N).normalised();
			Ray refRay = new Ray(P.add(N.scale(EPSILON)), refDir);
			ColorRGB reflectedIllumination = trace(scene, refRay, bouncesLeft - 1);
			*/

void main()
{
    vec3 C_diff = texture(tex, frag_texcoord).rgb;

	vec3 linear_color = vec3(0, 0, 0);
	// TODO: Calculate colour using Phong illumination model
    linear_color = C_diff * I_a; //Ambient




    //for(loop)
    {

        vec3 n = wc_frag_normal;
        vec3 l_i = lightPos - wc_frag_pos;
        vec3 v = wc_frag_pos - wc_camera_position;
        vec3 r_i = reflect(l_i, n);

        vec3 coord = n * -2 * dot(v, n) + (v);
        vec3 refCol = texture(skybox, coord).rgb;

        vec3 colDiff = C_diff * k_d * I_i * max(0, dot(normalize(n), normalize(l_i)));
        vec3 colSpec = C_spec * k_s * I_i * max(0, pow(dot(normalize(v),normalize(r_i)),(alpha)));
        linear_color = linear_color + colDiff + colSpec + refCol;
    }

	//linear_color = ambient + diffuse + specular;
	// TODO: Sample the texture and replace diffuse surface colour (C_diff) with texel value


	color = tonemap(linear_color);
}

