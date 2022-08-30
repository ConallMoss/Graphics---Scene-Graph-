#version 140

in vec3 frag_texcoord;			// texture UV coordinates

out vec3 color;			        // pixel colour

uniform samplerCube skybox; 		  // Cubemap texture sampler

// Tone mapping and display encoding combine
d
vec3 tonemap(vec3 linearRGB)
{
    float L_white = 0.7; // Controls the brightness of the image

    float inverseGamma = 1./2.2;
    return pow(linearRGB/L_white, vec3(inverseGamma)); // Display encoding - a gamma
}

void main()
{

	// TODO: Sample the skybox to determine the color of the environment
    vec3 C_diff = texture(skybox, frag_texcoord).rgb;
	vec3 linear_color = C_diff;//vec3(1,1,1);


	color = tonemap(linear_color);
}

